package com.yucheng.cmis.batch.diffdb.processor;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.diffdb.pojo.BatPospTCheckResult;


/**
 * 业务逻辑处理
 *
 */
public class BatPospTCheckResultProcessor implements ItemProcessor<BatPospTCheckResult, BatPospTCheckResult> {

	public BatPospTCheckResult process(BatPospTCheckResult obj) throws Exception {
		System.out.println("》》》》》》》》业务逻辑处理：主键："+obj.getEventid());
		return obj;
	}
}