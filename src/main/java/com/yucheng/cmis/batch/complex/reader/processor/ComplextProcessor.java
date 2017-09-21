package com.yucheng.cmis.batch.complex.reader.processor;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.common.pojo.TCredit;
import com.yucheng.cmis.batch.common.pojo.TDestcredit;


public class ComplextProcessor implements ItemProcessor<TCredit, TDestcredit> {

	public TDestcredit process(TCredit obj) throws Exception {
		//System.out.println("》》》》》》》》业务逻辑处理：主键："+obj.getId());
		
		TDestcredit ret =  new TDestcredit();
		ret.setId(obj.getId());
		ret.setAccountid(obj.getAccountid());
		ret.setAmount(obj.getAmount());
		ret.setAddress(obj.getAccountid());
		ret.setDate(obj.getDate());
		ret.setName(obj.getName());
		return ret;
	}

}
