package com.yucheng.cmis.batch.complex.reader;
import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


public class JobLaunchComplextReader {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("complex/reader/job-db-jdbc.xml", "dbReadJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
