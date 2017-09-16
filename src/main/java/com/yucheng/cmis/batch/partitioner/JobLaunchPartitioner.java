package com.yucheng.cmis.batch.partitioner;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * 
 * @author yuhq
 *
 */
public class JobLaunchPartitioner{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("partitioner/job/job-partition.xml", "partitionJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
