package com.yucheng.cmis.batch.complex.readers.processor;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.common.pojo.TDestcredit;
import com.yucheng.cmis.batch.common.pojo.TTradeRecord;
import com.yucheng.cmis.batch.complex.readers.pojo.TCreditComplex;

public class MyComplexProcessorForReader implements ItemProcessor<TCreditComplex, TDestcredit>{

	public TDestcredit process(TCreditComplex item) throws Exception {
		System.out.print(">>>>>>业务逻处理："+item.getId()+" || ");
		TDestcredit ret =  new TDestcredit();
		ret.setId(item.getId());
		ret.setAccountid(item.getAccountid());
		ret.setAmount(item.getAmount());
		ret.setAddress(item.getAccountid());
		ret.setDate(item.getDate());
		ret.setName(item.getName());
		
		List<TTradeRecord> list = item.getTtradeRecordList();
		if(list==null) {
			System.out.println("----------------子查询结果为空----------------");
		}else {
			System.out.println("子查询结果:");
			for (TTradeRecord obj : list) {
				System.out.println("id="+obj.getId()+",");
				System.out.println("accountid="+obj.getAccountid());
			}
		}
		
		return ret;
	}

}
