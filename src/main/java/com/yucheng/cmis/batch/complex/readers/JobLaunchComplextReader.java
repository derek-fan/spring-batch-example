package com.yucheng.cmis.batch.complex.readers;
import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


public class JobLaunchComplextReader {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("complex/readers/job/job-complex-readers.xml", "job-complex-readers",
				new JobParametersBuilder().addDate("date", new Date())
				.addString("_myTestParam", "dddddddddddd"));
	}
}
