package com.yucheng.cmis.batch.remote.partitioningstep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Step原理
 * 		Master将分区信息发送到MQ,Slave从MQ取得分区信息，执行分区操作，再将处理结果返回到MQ中
 * 
 * Maste端示例:
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
