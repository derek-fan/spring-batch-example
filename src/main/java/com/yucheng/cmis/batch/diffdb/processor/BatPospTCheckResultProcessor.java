package com.yucheng.cmis.batch.diffdb.processor;

import org.springframework.batch.item.ItemProcessor;

import com.yucheng.cmis.batch.diffdb.pojo.BatPospTCheckResult;


/**
 * ҵ���߼�����
 *
 */
public class BatPospTCheckResultProcessor implements ItemProcessor<BatPospTCheckResult, BatPospTCheckResult> {

	public BatPospTCheckResult process(BatPospTCheckResult obj) throws Exception {
		System.out.println("����������������ҵ���߼�����������"+obj.getEventid());
		return obj;
	}
}