package com.yucheng.cmis.batch.complex.readers.reader;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.InitializingBean;


/**
 * <p>
 * <h2>简述</h2>
 *     <ol>子查询默认实现类.</ol>
 * <h2>功能描述</h2>
 *     <ol>无.</ol>
 * <h2>修改历史</h2>
 *     <ol>无.</ol>
 * </p>
 */
public class MyBatisSubItemReader<P, S> implements InitializingBean {
	private static final Log LOGGER = LogFactory.getLog(MyBatisSubItemReader.class);
	private String queryId;
	private SqlSessionTemplate sqlSessionTemplate;
	// 继承的父查询的参数
	private Map<String, Object> pParameterValues;
	private Map<String, Object> parameterValues;
	// 将查询结果放入父对象的字段名称
	private String pField;
	// 父对象对应的主键字段名称，默认与fKField一致
	private String pPKField;
	// 当前子对象对应的外键字段名称
	private String fKField;

	// 查询预处理类，当默认传入的参数无法满足查询需要时，可以通过注入此类对参数对象进行修改
	private Prepare4MyBatisSubItemReader<P> parepare4SubReader;

	// 当前子查询是否为单条一条一条的进行查询，默认为false执行一次查询出所有结果
	private boolean oneByOne = false;

	public MyBatisSubItemReader() {
		super();
		LOGGER.debug("----init MyBatisSubItemReader----");
	}

	public void extendsConfig(SqlSessionTemplate sqlSessionTemplate, Map<String, Object> pParameterValues) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		this.pParameterValues = pParameterValues;
	}

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>读取字表数据并放入父对象相应的字段中.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param parents
	 * @throws Exception
	 */
	public void read(List<P> parents) throws Exception {
		List<S> subs = readSubs(parents);
		setSubs(parents, subs);;
	}

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>查询子对象内容.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>根据oneByOne的设置来区分执行一次还是多次.</ol>
	 *     <ol>返回所有符合子对象数据的集合.</ol>
	 * </p>
	 * @param parents
	 * @return
	 * @throws Exception 
	 */
	protected List<S> readSubs(List<P> parents) throws Exception {
		Map<String, Object> parameters = new HashMap<String, Object>();
		if (pParameterValues != null) {
			parameters.putAll(pParameterValues);
		}
		if (parameterValues != null) {
			parameters.putAll(parameterValues);
		}
		List<S> subList = new ArrayList<S>();
		// 如果是一条条的查询则执行list<P>.size()次，每次默认传入父对象的主键
		if (oneByOne) {
			for (P p : parents) {
				Object pkValue = getFieldValue(p, pPKField);
				parameters.put("_PARENT_PK", pkValue);
				if (parepare4SubReader != null) {
					parepare4SubReader.updateParameters4OneByOne(p, parameters);
				}
				List<S> oneSubList = sqlSessionTemplate.<S>selectList(queryId, parameters);
				subList.addAll(oneSubList);
			}
		} else {
			// 如果是一次性查询则执行1次，默认传入父对象的主键的集合
			List<Object> pPks = getPPks(parents);
			parameters.put("_PARENT_PKS", pPks);
			if (parepare4SubReader != null) {
				parepare4SubReader.updateParameters(parents, parameters);
			}
			subList = sqlSessionTemplate.<S>selectList(queryId, parameters);
		}
		return subList;
	}

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>将当前子表的查询结果按照主键拆分到父对象中.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param parents
	 * @param subs
	 * @throws Exception 
	 */
	protected void setSubs(List<P> parents, List<S> subs) throws Exception {
		if(subs.size()==0) return;
		for (P p : parents) {
			Object pPKValue = getFieldValue(p, pPKField);
			List<S> subs4Current = new ArrayList<S>();
			for (S s : subs) {
				Object fKValue = getFieldValue(s, fKField);
				if (pPKValue.toString().equals(fKValue.toString())) {
					subs4Current.add(s);
				}
			}
			setFieldValue(p, pField, subs4Current);
		}
	}

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>获取所有的主对象的主键集合.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param parents
	 * @return
	 * @throws Exception 
	 */
	protected List<Object> getPPks(List<P> parents) throws Exception {
		List<Object> pkValues = new ArrayList<Object>();
		for (P p : parents) {
			Object pkValue = getFieldValue(p, pPKField);
			pkValues.add(pkValue);
		}
		return pkValues;
	}
	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>获取指定字段的值.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param item
	 * @param fieldName
	 * @return 
	 */
	protected <X> Object getFieldValue(X item, String fieldName) throws Exception {
		Object value = null;
		String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = item.getClass().getMethod(getMethodName, new Class[]{});
		value = method.invoke(item, new Object[]{});
		return value;
	}

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>设置字段值.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param item
	 * @param fieldName
	 * @param value
	 * @throws Exception 
	 */
	protected <X> void setFieldValue(X item, String fieldName, Object value) throws Exception {
		String getMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		Method method = item.getClass().getMethod(getMethodName, new Class[]{List.class});
		method.invoke(item, new Object[]{value});
	}

	/**
	 * Check mandatory properties.
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		// notNull(queryId, "[Assertion failed] - this argument is required; it
		// must not be null");
		// notNull(fKField, "[Assertion failed] - this argument is required; it
		// must not be null");
		// 默认主表的字段名称与字表外键名称一致
		if (pPKField == null) {
			pPKField = fKField;
		}
	}

	public String getQueryId() {
		return queryId;
	}

	public void setQueryId(String queryId) {
		this.queryId = queryId;
	}

	public Map<String, Object> getParameterValues() {
		return parameterValues;
	}

	public void setParameterValues(Map<String, Object> parameterValues) {
		this.parameterValues = parameterValues;
	}

	public String getpField() {
		return pField;
	}

	public void setpField(String pField) {
		this.pField = pField;
	}

	public String getpPKField() {
		return pPKField;
	}

	public void setpPKField(String pPKField) {
		this.pPKField = pPKField;
	}

	public String getfKField() {
		return fKField;
	}

	public void setfKField(String fKField) {
		this.fKField = fKField;
	}

	public boolean isOneByOne() {
		return oneByOne;
	}

	public void setOneByOne(boolean oneByOne) {
		this.oneByOne = oneByOne;
	}

	public Prepare4MyBatisSubItemReader<P> getParepare4SubReader() {
		return parepare4SubReader;
	}

	public void setParepare4SubReader(Prepare4MyBatisSubItemReader<P> parepare4SubReader) {
		this.parepare4SubReader = parepare4SubReader;
	}

}
