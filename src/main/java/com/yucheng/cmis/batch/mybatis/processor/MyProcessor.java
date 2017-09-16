package com.yucheng.cmis.batch.mybatis.processor;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.mybatis.pojo.TCredit;
import com.yucheng.cmis.batch.mybatis.pojo.TDestcredit;


public class MyProcessor implements ItemProcessor<TCredit, TDestcredit> {

	public TDestcredit process(TCredit obj) throws Exception {
		System.out.println("����������������ҵ���߼�����������"+obj.getId());
		
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
