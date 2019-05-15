package com.ssm.chapter19.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import com.ssm.chapter19.redis.pojo.Role;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

public class Chapter19Main {

	public static void main(String[] args) {
		testTransaction();
		testPipeline();
		testJedisPipeline();
		testPubSub();
		testExpire();
		testLuaScript();
		testRedisScript();
		testLuaFile();
	}

	public static void testTransaction() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		SessionCallback callBack = (SessionCallback) (RedisOperations ops) -> {
			ops.multi();
			ops.boundValueOps("key1").set("value1");
			// 注意由于命令只是进入队列，而没有被执行，所以此处采用get命令，而value却返回为null
			String value = (String) ops.boundValueOps("key1").get();
			System.out.println("事务执行过程中，命令入队列，而没有被执行，所以value为空：value=" + value);
			// 此时list会保存之前进入队列的所有命令的结果
			List list = ops.exec();// 执行事务
			// 事务结束后，获取value1
			value = (String) redisTemplate.opsForValue().get("key1");
			return value;
		};
		// 执行Redis的命令
		String value = (String) redisTemplate.execute(callBack);
		System.out.println(value);
	}

	public static void testPipeline() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		// 使用Java8的Lambda表达式
		SessionCallback callBack = (SessionCallback) (RedisOperations ops) -> {
			for (int i = 0; i < 100000; i++) {
				int j = i + 1;
				ops.boundValueOps("pipeline_key_" + j).set("pipeline_value_" + j);
				ops.boundValueOps("pipeline_key_" + j).get();
			}
			return null;
		};
		long start = System.currentTimeMillis();
		// 执行Redis的流水线命令
		List resultList = redisTemplate.executePipelined(callBack);
		long end = System.currentTimeMillis();
		System.err.println(end - start);
	}

	private static JedisPool getPool() {
		JedisPoolConfig poolCfg = new JedisPoolConfig();
		// 最大空闲数
		poolCfg.setMaxIdle(50);
		// 最大连接数
		poolCfg.setMaxTotal(100);
		// 最大等待毫秒数
		poolCfg.setMaxWaitMillis(20000);
		// 使用配置创建连接池
		JedisPool pool = new JedisPool(poolCfg, "localhost");
		// 从连接池中获取单个连接
		Jedis jedis = pool.getResource();
		// 如果需密码
		// jedis.auth("password");
		return pool;
	}

	public static void testJedisPipeline() {
		JedisPool pool = getPool();
		Jedis jedis = pool.getResource();
		long start = System.currentTimeMillis();
		// 开启流水线
		Pipeline pipeline = jedis.pipelined();
		// 这里测试10万条的读写2个操作
		for (int i = 0; i < 100000; i++) {
			int j = i + 1;
			pipeline.set("pipeline_key_" + j, "pipeline_value_" + j);
			pipeline.get("pipeline_key_" + j);
		}
		// pipeline.sync();//这里只执行同步，但是不返回结果
		// pipeline.syncAndReturnAll();将返回执行过的命令返回的List列表结果
		List result = pipeline.syncAndReturnAll();
		long end = System.currentTimeMillis();
		// 计算耗时
		System.err.println("耗时：" + (end - start) + "毫秒");
	}

	public static void testPubSub() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		String channel = "chat";
		redisTemplate.convertAndSend(channel, "I am lazy!!");
	}

	public static void testExpire() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		redisTemplate.execute((RedisOperations ops) -> {
			ops.boundValueOps("key1").set("value1");
			String keyValue = (String) ops.boundValueOps("key1").get();
			Long expSecond = ops.getExpire("key1");
			System.err.println(expSecond);
			boolean b = false;
			b = ops.expire("key1", 120L, TimeUnit.SECONDS);
			b = ops.persist("key1");
			Long l = 0L;
			l = ops.getExpire("key1");
			Long now = System.currentTimeMillis();
			Date date = new Date();
			date.setTime(now + 120000);
			ops.expireAt("key", date);
			return null;
		});
	}

	public static void testLuaScript() {
		// 如果是简单的对象，使用原来的封装会简易些
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		// 如果是简单的操作，使用原来的Jedis会简易些
		Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
		// 执行简单的脚本
		String helloJava = (String) jedis.eval("return 'hello java'");
		System.out.println(helloJava);
		// 执行带参数的脚本
		jedis.eval("redis.call('set',KEYS[1], ARGV[1])", 1, "lua-key", "lua-value");
		String luaKey = (String) jedis.get("lua-key");
		System.out.println(luaKey);
		// 缓存脚本，返回sha1签名标识
		String sha1 = jedis.scriptLoad("redis.call('set',KEYS[1], ARGV[1])");
		// 通过标识执行脚本
		jedis.evalsha(sha1, 1, new String[] { "sha-key", "sha-val" });
		// 获取执行脚本后的数据
		String shaVal = jedis.get("sha-key");
		System.out.println(shaVal);
		// 关闭连接
		jedis.close();
	}

	public static void testRedisScript() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		// 定义默认脚本封装类
		DefaultRedisScript<Role> redisScript = new DefaultRedisScript<Role>();
		// 设置脚本
		redisScript.setScriptText("redis.call('set', KEYS[1], ARGV[1])  return redis.call('get', KEYS[1])");
		// 定义操作的key列表
		List<String> keyList = new ArrayList<String>();
		keyList.add("role1");
		// 需要序列化保存和读取的对象
		Role role = new Role();
		role.setId(1L);
		role.setRoleName("role_name_1");
		role.setNote("note_1");
		// 获得标识字符串
		String sha1 = redisScript.getSha1();
		System.out.println(sha1);
		// 设置返回结果类型，如果没有这句话，结果返回为空
		redisScript.setResultType(Role.class);
		// 定义序列化器
		JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
		// 执行脚本
		// 第一个是RedisScript接口对象，第二个是参数序列化器
		// 第三个是结果序列化器，第四个是Reids的key列表，最后是参数列表
		Role obj = (Role) redisTemplate.execute(redisScript, serializer, serializer, keyList, role);
		// 打印结果
		System.out.println(obj);
	}

	public static void testLuaFile() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		RedisTemplate redisTemplate = applicationContext.getBean(RedisTemplate.class);
		// 读入文件流
		File file = new File("G:\\dev\\redis\\test.lua");
		byte[] bytes = getFileToByte(file);
		Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
		// 发送文件二进制给Redis，这样REdis就会返回sha1标识
		byte[] sha1 = jedis.scriptLoad(bytes);
		// 使用返回的标识执行，其中第二个参数2，表示使用2个键
		// 而后面的字符串都转化为了二进制字节进行传输
		Object obj = jedis.evalsha(sha1, 2, "key1".getBytes(), "key2".getBytes(), "2".getBytes(), "4".getBytes());
		System.out.println(obj);
	}

	/**
	 * 把文件转化为二进制数组
	 * 
	 * @param file
	 *            文件
	 * @return 二进制数组
	 */
	public static byte[] getFileToByte(File file) {
		byte[] by = new byte[(int) file.length()];
		try {
			InputStream is = new FileInputStream(file);
			ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
			byte[] bb = new byte[2048];
			int ch;
			ch = is.read(bb);
			while (ch != -1) {
				bytestream.write(bb, 0, ch);
				ch = is.read(bb);
			}
			by = bytestream.toByteArray();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return by;
	}
}
