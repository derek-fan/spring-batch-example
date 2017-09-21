package com.yucheng.cmis.batch.complex.reader;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.batch.MyBatisPagingItemReader;

import com.yucheng.cmis.batch.common.pojo.TCredit;
import com.yucheng.cmis.batch.complex.reader.pojo.ComplextPojo;

/**
 * 
 * T会作为参数传入给processor
 * 
 *    现状：processor中需要查询12次数据库，因为需要查询的数据很多是一对多，不适宜配置在reader中。 processor中的查询次数过多，根据SpringBatch的chunk理念，如果processor中的查询也是面向块的，
 * 性能应该能有较大提升：即在Reader根据配置查询完一页数据（分页查询）后，将该页数据及processor中需要的数据一次查询完,如果分页配置是100条，原来在processor中一次要执行12*100次查询，现在只要执行12
 * 次查询。
 * 
 *    重写MyBatis的Reader，期望达到：根据mybatisReader的配置读取配置数据（称主业务数据）， 该Reader里根据主业务数据的主键来查询processor中需要的数据。那么需要定义一个复合对象，该复合对象含有主表、
 * 从表、子表的数据
 * 
 * @author yuhq
 *
 * @param <T>
 */
public class MyComplexMyBatisReader<T> extends MyBatisPagingItemReader<T>{
	private String keyName;
	private String subQueryId;
	

	@Override
	protected void doReadPage() {
		// 父类已经将配置的主SQL查看完成
		super.doReadPage();
		// 根据主数据结果集来获取子SQL的where查询条件
		List<Object> paramList = getSubSqlParameters();
		
		if(paramList.size()==0)
			return;
		
		List<TCredit> list =  sqlSessionTemplate.<TCredit> selectList(subQueryId, paramList);
		System.out.println(list.size());
		
	}
	
	/**
	 * 根据主数据结果集来获取子SQL的where查询条件
	 * 
	 * @return
	 */
	protected List<Object> getSubSqlParameters(){
		List<Object> paramList = new ArrayList<Object>();
		
		//results是mybatis查询的主SQL数据集
		for (T t : results) {
			if(t instanceof ComplextPojo) {
				paramList.add(((ComplextPojo) t).getValue(keyName));
			}
		}
		
		return paramList;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getSubQueryId() {
		return subQueryId;
	}

	public void setSubQueryId(String subQueryId) {
		this.subQueryId = subQueryId;
	}
	
}
