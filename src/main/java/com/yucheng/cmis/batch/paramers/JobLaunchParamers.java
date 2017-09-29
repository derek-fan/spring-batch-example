package com.yucheng.cmis.batch.paramers;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Launch���Σ�Step���Σ�XML�л�ȡ����
 * 
 * @author yuhq
 */
public class JobLaunchParamers{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JobParametersBuilder jobParamers = new JobParametersBuilder();
		//��Job��������������date��paramId
		//����paramId���Σ���ѯID>paramId������
		jobParamers.addDate("date", new Date()).addLong("paramId", new Long(100));
		
		//����Job
		JobLaunchBase.executeJob("paramers/job/job-paramers.xml", "paramersJob",jobParamers);
	}
}
