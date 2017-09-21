package com.yucheng.cmis.batch.complex.writers.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.complex.writers.pojo.TCredit;
import com.yucheng.cmis.batch.complex.writers.pojo.TDestcredit;
import com.yucheng.cmis.batch.complex.writers.pojo.TTradeRecord;

public class MyComplexProcessor implements ItemProcessor<TCredit, TDestcredit>{

	public TDestcredit process(TCredit item) throws Exception {
		TDestcredit ret =  new TDestcredit();
		ret.setId(item.getId());
		ret.setAccountid(item.getAccountid());
		ret.setAmount(item.getAmount());
		ret.setAddress(item.getAccountid());
		ret.setDate(item.getDate());
		ret.setName(item.getName());
		
		
		List<TTradeRecord> list = new ArrayList<TTradeRecord>();
		for (int i = 0; i < 3; i++) {
			TTradeRecord tradeRecord = new TTradeRecord();
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
