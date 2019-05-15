package com.ssm.chapter8.plugin;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import com.ssm.chapter8.params.PageParams;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PagePlugin implements Interceptor {
	/**
	 * 插件默认参数，可配置默认值.
	 */
	private Integer defaultPage; // 默认页码
	private Integer defaultPageSize;// 默认每页条数
	private Boolean defaultUseFlag; // 默认是否启用插件
	private Boolean defaultCheckFlag; // 默认是否检测页码参数
	private Boolean defaultCleanOrderBy; // 默认是否清除最后一个order by 后的语句

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler stmtHandler = (StatementHandler) getUnProxyObject(invocation.getTarget());
		MetaObject metaStatementHandler = SystemMetaObject.forObject(stmtHandler);
		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		// 不是select语句
		if (!checkSelect(sql)) {
			return invocation.proceed();
		}
		BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
		Object parameterObject = boundSql.getParameterObject();
		PageParams pageParams = getPageParamsForParamObj(parameterObject);
		if (pageParams == null) { // 无法获取分页参数，不进行分页
			return invocation.proceed();
		}

		// 获取配置中是否启用分页功能
		Boolean useFlag = pageParams.getUseFlag() == null ? this.defaultUseFlag : pageParams.getUseFlag();
		if (!useFlag) { // 不使用分页插件
			return invocation.proceed();
		}
		// 获取相关配置的参数
		Integer pageNum = pageParams.getPage() == null ? defaultPage : pageParams.getPage();
		Integer pageSize = pageParams.getPageSize() == null ? defaultPageSize : pageParams.getPageSize();
		Boolean checkFlag = pageParams.getCheckFlag() == null ? defaultCheckFlag : pageParams.getCheckFlag();
		Boolean cleanOrderBy = pageParams.getCleanOrderBy() == null ? defaultCleanOrderBy
				: pageParams.getCleanOrderBy();
		// 计算总条数
		int total = getTotal(invocation, metaStatementHandler, boundSql, cleanOrderBy);
		// 回填总条数到分页参数
		pageParams.setTotal(total);
		// 计算总页数.
		int totalPage = total % pageSize == 0 ? total / pageSize : total / pageSize + 1;
		// 回填总页数到分页参数
		pageParams.setTotalPage(totalPage);
		// 检查当前页码的有效性
		checkPage(checkFlag, pageNum, totalPage);
		// 修改sql
		return preparedSQL(invocation, metaStatementHandler, boundSql, pageNum, pageSize);
	}

	/**
	 * 从代理对象中分离出真实对象
	 * 
	 * @param ivt
	 *            --Invocation
	 * @return 非代理StatementHandler对象
	 */
	private Object getUnProxyObject(Object target) {
		MetaObject metaStatementHandler = SystemMetaObject.forObject(target);
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过循环可以分离出最原始的目标类)
		Object object = null;
		
		// 可以分离出最原始的的目标类)  
        while (metaStatementHandler.hasGetter("h")) {  
            object = metaStatementHandler.getValue("h");  
            metaStatementHandler = SystemMetaObject.forObject(object);  
        }  
        
		if (object == null) {
			return target;
		}
		return object;
	}

	/**
	 * 判断是否sql语句
	 *
	 * @param sql
	 *            --当前执行SQL
	 * @return 是否查询语句
	 */
	private boolean checkSelect(String sql) {
		String trimSql = sql.trim();
		int idx = trimSql.toLowerCase().indexOf("select");
		return idx == 0;
	}

	/**
	 * * 分离出分页参数
	 * 
	 * @param parameterObject
	 *            --执行参数
	 * @return 分页参数
	 * @throws Exception
	 */
	public PageParams getPageParamsForParamObj(Object parameterObject) throws Exception {
		PageParams pageParams = null;
		if (parameterObject == null) {
			return null;
		}
		// 处理map参数，多个匿名参数和@Param注解参数，都是map
		if (parameterObject instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, Object> paramMap = (Map<String, Object>) parameterObject;
			Set<String> keySet = paramMap.keySet();
			Iterator<String> iterator = keySet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = paramMap.get(key);
				if (value instanceof PageParams) {
					return (PageParams) value;
				}
			}
		} else if (parameterObject instanceof PageParams) { // 参数是或者继承PageParams
			return (PageParams) parameterObject;
		} else { // 从POJO属性尝试读取分页参数
			Field[] fields = parameterObject.getClass().getDeclaredFields();
			// 尝试从POJO中获得类型为PageParams的属性
			for (Field field : fields) {
				if (field.getType() == PageParams.class) {
					PropertyDescriptor pd = new PropertyDescriptor(field.getName(), parameterObject.getClass());
					Method method = pd.getReadMethod();
					return (PageParams) method.invoke(parameterObject);
				}
			}
		}
		return pageParams;
	}

	/**
	 * 获取总条数.
	 * 
	 * @param ivt
	 *            Invocation 入参
	 * @param metaStatementHandler
	 *            statementHandler
	 * @param boundSql
	 *            sql
	 * @param cleanOrderBy
	 *            是否清除order by语句
	 * @return sql查询总数.
	 * @throws Throwable
	 *             异常.
	 */
	private int getTotal(Invocation ivt, MetaObject metaStatementHandler, BoundSql boundSql, Boolean cleanOrderBy)
			throws Throwable {
		// 获取当前的mappedStatement
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
		// 配置对象
		Configuration cfg = mappedStatement.getConfiguration();
		// 当前需要执行的SQL
		String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
		// 去掉最后的order by语句
		if (cleanOrderBy) {
			sql = this.cleanOrderByForSql(sql);
		}
		// 改写为统计总数的SQL
		String countSql = "select count(*) as total from (" + sql + ") $_paging";
		// 获取拦截方法参数，根据插件签名，知道是Connection对象
		Connection connection = (Connection) ivt.getArgs()[0];
		PreparedStatement ps = null;
		int total = 0;
		try {
			// 预编译统计总数SQL
			ps = connection.prepareStatement(countSql);
			// 构建统计总数BoundSql
			BoundSql countBoundSql = new BoundSql(cfg, countSql, boundSql.getParameterMappings(),
					boundSql.getParameterObject());
			// 构建MyBatis的ParameterHandler用来设置总数Sql的参数
			ParameterHandler handler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(),
					countBoundSql);
			// 设置总数SQL参数
			handler.setParameters(ps);
			// 执行查询.
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				total = rs.getInt("total");
			}
		} finally {
			// 这里不能关闭Connection，否则后续的SQL就没法继续了
			if (ps != null) {
				ps.close();
			}
		}
		return total;
	}

	private String cleanOrderByForSql(String sql) {
		StringBuilder sb = new StringBuilder(sql);
		String newSql = sql.toLowerCase();
		// 如果没有order语句,直接返回
		if (newSql.indexOf("order") == -1) {
			return sql;
		}
		int idx = newSql.lastIndexOf("order");
		return sb.substring(0, idx).toString();
	}

	/**
	 * 检查当前页码的有效性
	 *
	 * @param checkFlag
	 *            检测标志
	 * @param pageNum
	 *            当前页码
	 * @param pageTotal
	 *            最大页码
	 * @throws Throwable
	 */
	private void checkPage(Boolean checkFlag, Integer pageNum, Integer pageTotal) throws Throwable {
		if (checkFlag) {
			// 检查页码page是否合法
			if (pageNum > pageTotal) {
				throw new Exception("查询失败，查询页码【" + pageNum + "】大于总页数【" + pageTotal + "】！！");
			}
		}
	}

	/**
	 * 预编译改写后的SQL，并设置分页参数
	 *
	 * @param invocation
	 *            入参
	 * @param metaStatementHandler
	 *            MetaObject绑定的StatementHandler
	 * @param boundSql
	 *            boundSql对象
	 * @param pageNum
	 *            当前页
	 * @param pageSize
	 *            最大页
	 * @throws IllegalAccessException
	 *             异常
	 * @throws InvocationTargetException
	 *             异常
	 */
	private Object preparedSQL(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, int pageNum,
			int pageSize) throws Exception {
		// 获取当前需要执行的SQL
		String sql = boundSql.getSql();
		String newSql = "select * from (" + sql + ") $_paging_table limit ?, ?";
		// 修改当前需要执行的SQL
		metaStatementHandler.setValue("delegate.boundSql.sql", newSql);
		// 执行编译，相当于StatementHandler执行了prepared()方法，这个时候，就剩下两个分页参数没有设置
		Object statementObj = invocation.proceed();
		// 设置两个分页参数
		this.preparePageDataParams((PreparedStatement) statementObj, pageNum, pageSize);
		return statementObj;
	}

	/**
	 * 使用PreparedStatement预编译两个分页参数，如果数据库的规则不一样，需要改写设置的参数规则
	 *
	 * @throws SQLException
	 * @throws NotSupportedException
	 *
	 */
	private void preparePageDataParams(PreparedStatement ps, int pageNum, int pageSize) throws Exception {
		// prepared()方法编译SQL，由于MyBatis上下文没有分页参数的信息，所以这里需要设置这两个参数
		// 获取需要设置的参数个数，由于参数是最后的两个，所以很容易得到其位置
		int idx = ps.getParameterMetaData().getParameterCount();
		// 最后两个是我们的分页参数
		ps.setInt(idx - 1, (pageNum - 1) * pageSize);// 开始行
		ps.setInt(idx, pageSize); // 限制条数
	}

	@Override
	public Object plugin(Object target) {
		// 生成代理对象.
		return Plugin.wrap(target, this);
	}

	/**
	 * 设置插件配置参数。
	 *
	 * @param props
	 *            配置参数
	 */
	@Override
	public void setProperties(Properties props) {
		// 从配置中获取参数
		String strDefaultPage = props.getProperty("default.page", "1");
		String strDefaultPageSize = props.getProperty("default.pageSize", "50");
		String strDefaultUseFlag = props.getProperty("default.useFlag", "false");
		String strDefaultCheckFlag = props.getProperty("default.checkFlag", "false");
		String StringDefaultCleanOrderBy = props.getProperty("default.cleanOrderBy", "false");
		// 设置默认参数.
		this.defaultPage = Integer.parseInt(strDefaultPage);
		this.defaultPageSize = Integer.parseInt(strDefaultPageSize);
		this.defaultUseFlag = Boolean.parseBoolean(strDefaultUseFlag);
		this.defaultCheckFlag = Boolean.parseBoolean(strDefaultCheckFlag);
		this.defaultCleanOrderBy = Boolean.parseBoolean(StringDefaultCleanOrderBy);
	}
}