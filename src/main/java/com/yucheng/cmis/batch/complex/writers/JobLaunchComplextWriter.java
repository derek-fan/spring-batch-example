package com.yucheng.cmis.batch.complex.writers;
import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


public class JobLaunchComplextWriter {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("complex/writers/job-complex-writer2.xml", "dbComplexWriterJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
