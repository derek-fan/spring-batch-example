package com.yucheng.cmis.batch.remote.singlestep.processor;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.mybatis.pojo.TCredit;
import com.yucheng.cmis.batch.mybatis.pojo.TDestcredit;


public class MyProcessor implements ItemProcessor<TCredit, TDestcredit> {

	public TDestcredit process(TCredit obj) throws Exception {
		
		Thread.sleep(2000);
		
		System.out.println("》》》》》》》》业务逻辑处理：主键："+obj.getId());
		
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
