package com.yucheng.cmis.batch.remote.singlestep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Step原理
 * 		Maste将数据发送到MQ上，Slave(远程)监听MQ,从队列中取数据处理，将结果再通过队列返回给Maste
 * 
 * Maste端示例:
 * 
 * 
 * @author yuhq
 *
 */
public class JobLaunchChunkMaste {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("remote/singlestep/maste/job/job-chunk-maste.xml", "masteChunkJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
