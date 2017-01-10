/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.xiaozhameng.base.mybatis.interceptor;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.xiaozhameng.common.entity.Page;

/**
 * 数据库分页插件，只拦截查询语句.
 * 
 * @author poplar.yfyang / thinkgem
 * @version 2013-8-28
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class PaginationInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 1L;

	public Object intercept(Invocation invocation) throws Throwable {
		final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = invocation.getArgs()[1];
		BoundSql boundSql = mappedStatement.getBoundSql(parameter);
		Object parameterObject = boundSql.getParameterObject();
		// 获取分页参数对象
		Page<Object> page = null;
		if (parameterObject != null) {
			page = convertParameter(parameterObject, page);
		}
		// 如果设置了分页对象，则进行分页
		if (page != null && page.getPageSize() != -1) {
			if (StringUtils.isBlank(boundSql.getSql())) {
				return null;
			}
			String originalSql = boundSql.getSql().trim();
			
			//TODO 得到总记录数----------配置上连接池
			if(page.getCount()==-1){
		         page.setCount(SQLHelper.getCount(originalSql, null, mappedStatement, parameterObject, boundSql, log));
			}
			//添加ORDER BY
			if (page.getOrderBy() != null && !page.getOrderBy().equals("")) {
				originalSql = originalSql + " ORDER BY " + page.getOrderBy();
			}
			
			// 分页查询 本地化对象 修改数据库注意修改实现---------拼接PAGE查询参数
			String pageSql = SQLHelper.generatePageSql(originalSql, page, DIALECT);

			invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
			BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql,
					boundSql.getParameterMappings(), boundSql.getParameterObject());
			Configuration configuration = mappedStatement.getConfiguration();
			List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
			MetaObject metaObject = null;//
			try {
				metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			} catch (Exception e) {
			}
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value = null;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(
									propertyName.substring(prop.getName().length()));
						}
					} else if (metaObject != null) {
						try {
							value = metaObject == null ? null : metaObject.getValue(propertyName);
						} catch (Exception e) {
							value = boundSql.getAdditionalParameter(propertyName);
						}
					}
					newBoundSql.setAdditionalParameter(propertyName, value);
				}
			}

			MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
			invocation.getArgs()[0] = newMs;
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		super.initProperties(properties);
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource,
				ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			for (String keyProperty : ms.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		return builder.build();
	}

	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;

		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}

		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
}
