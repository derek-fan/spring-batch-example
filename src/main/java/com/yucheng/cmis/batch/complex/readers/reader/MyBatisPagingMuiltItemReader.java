package com.yucheng.cmis.batch.complex.readers.reader;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ClassUtils.getShortName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.batch.item.database.AbstractPagingItemReader;

/**
 * <p>
 * <h2>简述</h2>
 *     <ol>基于MyBatis的分页查询，并支持子查询.</ol>
 * <h2>功能描述</h2>
 *     <ol>无.</ol>
 * <h2>修改历史</h2>
 *     <ol>无.</ol>
 * </p>
 */
public class MyBatisPagingMuiltItemReader<T> extends AbstractPagingItemReader<T> {
	private static final Log LOGGER = LogFactory.getLog(MyBatisPagingMuiltItemReader.class);
	private String queryId;
	private SqlSessionFactory sqlSessionFactory;
	private SqlSessionTemplate sqlSessionTemplate;
	//SQL的额外传参，可以从上下文中传进来
	private Map<String, Object> parameterValues;
	private List<? extends MyBatisSubItemReader<T, ?>> subReaders;

	public MyBatisPagingMuiltItemReader() {
		LOGGER.debug("----init MyBatisPagingMuiltItemReader----");
		setName(getShortName(MyBatisPagingMuiltItemReader.class));
	}

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>读取一页数据.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 */
	@Override
	protected void doReadPage() {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (parameterValues != null) {
				parameters.putAll(parameterValues);
			}
			// 添加分页参数
			parameters.put("_page", getPage());
			parameters.put("_pagesize", getPageSize());
			parameters.put("_skiprows", getPage() * getPageSize());
			if (results == null) {
				results = new CopyOnWriteArrayList<T>();
			} else {
				results.clear();
			}
			// 主表查询
			List<T> list = sqlSessionTemplate.<T>selectList(queryId, parameters);
			// 如果配置了子查询，则按照顺序执行
			if (list != null && !list.isEmpty() && subReaders != null) {
				for (MyBatisSubItemReader<T, ?> subReader : subReaders) {
					subReader.extendsConfig(sqlSessionTemplate, parameters);
					subReader.read(list);
				}
			}
			results.addAll(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Check mandatory properties.
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();
		notNull(sqlSessionFactory, "[Assertion failed] - this argument is required; it must not be null");
		sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
		notNull(queryId, "[Assertion failed] - this argument is required; it must not be null");
	}

	@Override
	protected void doJumpToPage(int itemIndex) {
		// Not Implemented
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Map<String, Object> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(Map<String, Object> parameterValues) {
		this.parameterValues = parameterValues;
	}

	public List<? extends MyBatisSubItemReader<T, ?>> getSubReaders() {
		return subReaders;
	}

	public void setSubReaders(List<? extends MyBatisSubItemReader<T, ?>> subReaders) {
		this.subReaders = subReaders;
	}

}
