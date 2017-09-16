package com.yucheng.cmis.batch.diffdb;
import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


public class JobLaunchIbatis {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("diffdb/job-db-jdbc.xml", "dbReadJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
