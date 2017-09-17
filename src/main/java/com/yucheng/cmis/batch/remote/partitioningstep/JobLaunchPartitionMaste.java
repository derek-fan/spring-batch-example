package com.yucheng.cmis.batch.remote.partitioningstep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Stepԭ��
 * 		Master��������Ϣ���͵�MQ,Slave��MQȡ�÷�����Ϣ��ִ�з����������ٽ����������ص�MQ��
 * 
 * Maste��ʾ��:
 * 
 * 
 * @author yuhq
 *
 */
public class JobLaunchPartitionMaste {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("remote/partitioningstep/maste/job/job-chunk-partition-maste.xml", "mastePartitionJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
