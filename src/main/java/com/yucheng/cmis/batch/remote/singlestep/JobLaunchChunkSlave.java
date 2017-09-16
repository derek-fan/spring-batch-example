package com.yucheng.cmis.batch.remote.singlestep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Step原理
 * 		Maste将数据发送到MQ上，Slave(远程)监听MQ,从队列中取数据处理，将结果再通过队列返回给Maste
 * 
 * Slave端示例:
 * 
 * 
 * 注:slave端无需记录处理结果到数据库，所以配置基于内存的仓库
 * org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean
 * @author yuhq
 *
 */
public class JobLaunchChunkSlave{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("remote/singlestep/slave/job/job-chunk-slave.xml", "remoteChunkJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
