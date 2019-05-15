package com.ssm.chapter19.redis.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageListener implements MessageListener {
	
	private RedisTemplate redisTemplate;

	@Override
	public void onMessage(Message message, byte[] bytes) {
		// 获取消息
		byte[] body = message.getBody();
		// 使用值序列化器转换
		String msgBody = (String) getRedisTemplate().getValueSerializer().deserialize(body);
		System.err.println(msgBody);
		// 获取channel
		byte[] channel = message.getChannel();
		// 使用字符串序列化器转换
		String channelStr = (String) getRedisTemplate().getStringSerializer().deserialize(channel);
		System.err.println(channelStr);
		// 渠道名称转换
		String bytesStr = new String(bytes);
		System.err.println(bytesStr);
	}

	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
}
