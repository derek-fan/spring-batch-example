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
 * 这里的ItemWriter与一般的Writer不同之处是从主模型中取得当前ItemWriter需要的数据，
 * 并做校验，然后将实际的数据写入操作委派给MyBatisBatchItemWriter
 *
 */
public class MyBatisComplexItemWriter<T> implements ItemWriter<T>{
	private static final Log LOGGER = LogFactory.getLog(MyBatisBatchItemWriter.class);
	
	//被委派的ItemWriter
	MyBatisBatchItemWriter<T> mybatisWriter;
	//校验字段
	private String checkField;
	//取值字段
	private String paramField;
	//判断当前writer是处理子表还是从表
	private boolean flag = false;
	
	/**
	 * 实际的数据写入操作还是委派给mybatisWriter。
	 * 在委派之前做数据有效性校验及从主模型中取得当前writer需要的数据
	 */
	@Override
	public void write(List<? extends T> items) throws Exception {
		
		List list = new ArrayList<>();
		for (T item : items) {
			//校验
			if (checkField(item)) {
				Object objects = getUpdateParam(item);
				//从表操作，一对多
				if(objects instanceof List) {
					flag = true;
					List tempItems = (List)objects;
					
					//委派
					mybatisWriter.write(tempItems);
				}
				
				list.add(getUpdateParam(item));
			} else {
				LOGGER.debug("数据校验不通过，跳过该条数据...");
			}
		}
		
		//子表操作：一对一
		if(!flag) {
			//委派
			mybatisWriter.write(list);
		}
	}

	/**
	 * 从主模型中取得当前writer需要的数据
	 * @param item 主模型
	 * @return 当前writer需要的数据
	 */
	protected Object getUpdateParam(T item) {
		// 没有配置时传入原始对象
		if (paramField == null || "".equals(paramField.trim())) {
			return item;
		}
		try {
			String getMethodName = "get" + paramField.substring(0, 1).toUpperCase() + paramField.substring(1);
			Method method = item.getClass().getMethod(getMethodName, new Class<?>[]{});
			Object fieldValue = method.invoke(item, new Object[]{});
			
			return fieldValue;
		} catch (Exception e) {
			LOGGER.error("获取对象属性值出错 " + e, e);
		}
		return item;
	}
	
	/**
	 * 校验需要需要执行当前writer 
	 * @param item item 主模型
	 * @return true:处理； false：处理
	 */
	protected boolean checkField(T item) {
		// 不需要校验
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
			LOGGER.error("数据校验出错 " + e, e);
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
