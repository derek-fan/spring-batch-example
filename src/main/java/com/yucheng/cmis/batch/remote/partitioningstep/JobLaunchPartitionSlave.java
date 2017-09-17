package com.yucheng.cmis.batch.remote.partitioningstep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Stepԭ��
 * 		Master��������Ϣ���͵�MQ,Slave��MQȡ�÷�����Ϣ��ִ�з����������ٽ����������ص�MQ��
 * 
 * Slave��ʾ��:
 * 
 * 
 * @author yuhq
 *
 */
public class JobLaunchPartitionSlave {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("remote/partitioningstep/slave/job/job-chunk-partition-slave.xml", "slavePartitionJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
