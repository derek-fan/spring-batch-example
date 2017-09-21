package com.yucheng.cmis.batch.complex.writers.writer;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.item.ItemWriter;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

/**
 * �����ItemWriter��һ���Writer��֮ͬ���Ǵ���ģ����ȡ�õ�ǰItemWriter��Ҫ�����ݣ�
 * ����У�飬Ȼ��ʵ�ʵ�����д�����ί�ɸ�MyBatisBatchItemWriter
 *
 */
public class MyBatisComplexItemWriter<T> implements ItemWriter<T>{
	private static final Log LOGGER = LogFactory.getLog(MyBatisBatchItemWriter.class);
	
	//��ί�ɵ�ItemWriter
	MyBatisBatchItemWriter<T> mybatisWriter;
	//У���ֶ�
	private String checkField;
	//ȡֵ�ֶ�
	private String paramField;
	//�жϵ�ǰwriter�Ǵ����ӱ��Ǵӱ�
	private boolean flag = false;
	
	/**
	 * ʵ�ʵ�����д���������ί�ɸ�mybatisWriter��
	 * ��ί��֮ǰ��������Ч��У�鼰����ģ����ȡ�õ�ǰwriter��Ҫ������
	 */
	@Override
	public void write(List<? extends T> items) throws Exception {
		
		List list = new ArrayList<>();
		for (T item : items) {
			//У��
			if (checkField(item)) {
				Object objects = getUpdateParam(item);
				//�ӱ������һ�Զ�
				if(objects instanceof List) {
					flag = true;
					List tempItems = (List)objects;
					
					//ί��
					mybatisWriter.write(tempItems);
				}
				
				list.add(getUpdateParam(item));
			} else {
				LOGGER.debug("����У�鲻ͨ����������������...");
			}
		}
		
		//�ӱ������һ��һ
		if(!flag) {
			//ί��
			mybatisWriter.write(list);
		}
	}

	/**
	 * ����ģ����ȡ�õ�ǰwriter��Ҫ������
	 * @param item ��ģ��
	 * @return ��ǰwriter��Ҫ������
	 */
	protected Object getUpdateParam(T item) {
		// û������ʱ����ԭʼ����
		if (paramField == null || "".equals(paramField.trim())) {
			return item;
		}
		try {
			String getMethodName = "get" + paramField.substring(0, 1).toUpperCase() + paramField.substring(1);
			Method method = item.getClass().getMethod(getMethodName, new Class<?>[]{});
			Object fieldValue = method.invoke(item, new Object[]{});
			
			return fieldValue;
		} catch (Exception e) {
			LOGGER.error("��ȡ��������ֵ���� " + e, e);
		}
		return item;
	}
	
	/**
	 * У����Ҫ��Ҫִ�е�ǰwriter 
	 * @param item item ��ģ��
	 * @return true:���� false������
	 */
	protected boolean checkField(T item) {
		// ����ҪУ��
		if (checkField == null || "".equals(checkField.trim())) {
			return true;
		}
		try {
			String getMethodName = "get" + checkField.substring(0, 1).toUpperCase() + checkField.substring(1);
			Method method = item.getClass().getMethod(getMethodName, new Class<?>[]{});
			Object fieldValue = method.invoke(item, new Object[]{});
			if (fieldValue == null) {
				return false;
			} else if (fieldValue instanceof Collection) {
				Collection<?> cv = (Collection<?>) fieldValue;
				if (cv.isEmpty()) {
					return false;
				}
			}
		} catch (Exception e) {
			LOGGER.error("����У����� " + e, e);
		}
		return true;
	}

//	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
//		this.sqlSessionTemplate = sqlSessionTemplate;
//	}

	public void setMybatisWriter(MyBatisBatchItemWriter<T> mybatisWriter) {
		this.mybatisWriter = mybatisWriter;
	}

	public void setCheckField(String checkField) {
		this.checkField = checkField;
	}

	public void setParamField(String paramField) {
		this.paramField = paramField;
	}
}
