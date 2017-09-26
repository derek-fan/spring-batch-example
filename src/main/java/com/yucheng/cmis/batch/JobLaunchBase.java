package com.yucheng.cmis.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobLaunchBase {
	public static void executeJob(String jobPath, String jobName, JobParametersBuilder builder) {
		ApplicationContext context = new ClassPathXmlApplicationContext(jobPath);
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean(jobName);
		try {
			JobExecution result = launcher.run(job, builder.toJobParameters());
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		String jobName = "dbReadJob";//Job����
		String jobPath = "mybatis/job-db-jdbc.xml";//���ص������ļ�
				
		ApplicationContext context = new ClassPathXmlApplicationContext(jobPath);
		JobOperator operator = (JobOperator)context.getBean("jobOperator");
		//����Job��������job���ƣ�����
		Long result = operator.start(jobName, "test1");
		System.out.println("Job Instance id = "+result);
	}
}
