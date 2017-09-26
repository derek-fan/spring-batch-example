package com.yucheng.cmis.batch.flow;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * À≥–ÚFlow
 * @author yuhq
 *
 */
public class JobLaunchFlowSequential{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("flow/job/job-flow-sequential.xml", "sequentialFlowJog",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}