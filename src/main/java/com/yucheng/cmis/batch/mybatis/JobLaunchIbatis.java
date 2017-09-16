package com.yucheng.cmis.batch.mybatis;
import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


public class JobLaunchIbatis {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("mybatis/job-db-jdbc.xml", "dbReadJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
