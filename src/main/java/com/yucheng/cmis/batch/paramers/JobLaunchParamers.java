package com.yucheng.cmis.batch.paramers;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Launch传参，Step传参，XML中获取参数
 * 
 * @author yuhq
 */
public class JobLaunchParamers{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JobParametersBuilder jobParamers = new JobParametersBuilder();
		//给Job设置两个参数：date和paramId
		//利用paramId传参，查询ID>paramId的数据
		jobParamers.addDate("date", new Date()).addLong("paramId", new Long(100));
		
		//启动Job
		JobLaunchBase.executeJob("paramers/job/job-paramers.xml", "paramersJob",jobParamers);
	}
}
