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
 * <h2>����</h2>
 *     <ol>�Ӳ�ѯĬ��ʵ����.</ol>
 * <h2>��������</h2>
 *     <ol>��.</ol>
 * <h2>�޸���ʷ</h2>
 *     <ol>��.</ol>
 * </p>
 */
public class MyBatisSubItemReader<P, S> implements InitializingBean {
	private static final Log LOGGER = LogFactory.getLog(MyBatisSubItemReader.class);
	private String queryId;
	private SqlSessionTemplate sqlSessionTemplate;
	// �̳еĸ���ѯ�Ĳ���
	private Map<String, Object> pParameterValues;
	private Map<String, Object> parameterValues;
	// ����ѯ������븸������ֶ�����
	private String pField;
	// �������Ӧ�������ֶ����ƣ�Ĭ����fKFieldһ��
	private String pPKField;
	// ��ǰ�Ӷ����Ӧ������ֶ�����
	private String fKField;

	// ��ѯԤ�����࣬��Ĭ�ϴ���Ĳ����޷������ѯ��Ҫʱ������ͨ��ע�����Բ�����������޸�
	private Prepare4MyBatisSubItemReader<P> parepare4SubReader;

	// ��ǰ�Ӳ�ѯ�Ƿ�Ϊ����һ��һ���Ľ��в�ѯ��Ĭ��Ϊfalseִ��һ�β�ѯ�����н��
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
	 * <h2>����</h2>
	 *     <ol>��ȡ�ֱ����ݲ����븸������Ӧ���ֶ���.</ol>
	 * <h2>��������</h2>
	 *     <ol>��.</ol>
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
	 * <h2>����</h2>
	 *     <ol>��ѯ�Ӷ�������.</ol>
	 * <h2>��������</h2>
	 *     <ol>����oneByOne������������ִ��һ�λ��Ƕ��.</ol>
	 *     <ol>�������з����Ӷ������ݵļ���.</ol>
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
		// �����һ�����Ĳ�ѯ��ִ��list<P>.size()�Σ�ÿ��Ĭ�ϴ��븸���������
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
			// �����һ���Բ�ѯ��ִ��1�Σ�Ĭ�ϴ��븸����������ļ���
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
	 * <h2>����</h2>
	 *     <ol>����ǰ�ӱ�Ĳ�ѯ�������������ֵ���������.</ol>
	 * <h2>��������</h2>
	 *     <ol>��.</ol>
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
	 * <h2>����</h2>
	 *     <ol>��ȡ���е����������������.</ol>
	 * <h2>��������</h2>
	 *     <ol>��.</ol>
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
	 * <h2>����</h2>
	 *     <ol>��ȡָ���ֶε�ֵ.</ol>
	 * <h2>��������</h2>
	 *     <ol>��.</ol>
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
	 * <h2>����</h2>
	 *     <ol>�����ֶ�ֵ.</ol>
	 * <h2>��������</h2>
	 *     <ol>��.</ol>
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
		// Ĭ��������ֶ��������ֱ��������һ��
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
