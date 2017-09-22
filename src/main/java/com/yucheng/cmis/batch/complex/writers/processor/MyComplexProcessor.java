package com.yucheng.cmis.batch.complex.writers.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.common.pojo.TCredit;
import com.yucheng.cmis.batch.common.pojo.TDestTradeRecord;
import com.yucheng.cmis.batch.complex.writers.pojo.TDestcreditComplex;

public class MyComplexProcessor implements ItemProcessor<TCredit, TDestcreditComplex>{

	public TDestcreditComplex process(TCredit item) throws Exception {
		TDestcreditComplex ret =  new TDestcreditComplex();
		ret.setId(item.getId());
		ret.setAccountid(item.getAccountid());
		ret.setAmount(item.getAmount());
		ret.setAddress(item.getAccountid());
		ret.setDate(item.getDate());
		ret.setName(item.getName());
		
		
		List<TDestTradeRecord> list = new ArrayList<TDestTradeRecord>();
		for (int i = 0; i < 3; i++) {
			TDestTradeRecord tradeRecord = new TDestTradeRecord();
//			tradeRecord.setId(1000);
			tradeRecord.setAccountid(Integer.valueOf(item.getId()));
			tradeRecord.setTradeAddress(item.getAddress());
			tradeRecord.setTradeType("0");
			
			list.add(tradeRecord);
		}
		
		ret.setTtradeRecordList(list);
		
		System.out.println(">>>>>>业务逻处理："+item.getId());
		
		return ret;
	}

}
