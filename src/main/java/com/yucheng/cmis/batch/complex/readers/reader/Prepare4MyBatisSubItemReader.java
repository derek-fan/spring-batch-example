package com.yucheng.cmis.batch.complex.readers.reader;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

/**
 * <p>
 * <h2>简述</h2>
 *     <ol>子查询查询前数据处理类.</ol>
 * <h2>功能描述</h2>
 *     <ol>无.</ol>
 * <h2>修改历史</h2>
 *     <ol>无.</ol>
 * </p>
 */
public class Prepare4MyBatisSubItemReader<P> implements InitializingBean {

	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>设置oneByOne=true时，在每次查询调用前会被触发.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param p
	 * @param parameters 
	 */
	public void updateParameters4OneByOne(P p, Map<String, Object> parameters) {

	}
	
	/**
	 * <p>
	 * <h2>简述</h2>
	 *     <ol>oneByOne=false时，在查询调用前会被触发.</ol>
	 * <h2>功能描述</h2>
	 *     <ol>无.</ol>
	 * </p>
	 * @param p
	 * @param parameters 
	 */
	public void updateParameters(List<P> p, Map<String, Object> parameters) {

	}
	
	@Override
	public void afterPropertiesSet() throws Exception {

	}

}
