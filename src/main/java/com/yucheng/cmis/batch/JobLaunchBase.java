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
		String jobName = "dbReadJob";//Job名称
		String jobPath = "mybatis/job-db-jdbc.xml";//加载的配置文件
				
		ApplicationContext context = new ClassPathXmlApplicationContext(jobPath);
		JobOperator operator = (JobOperator)context.getBean("jobOperator");
		//启动Job，参数：job名称，参数
		Long result = operator.start(jobName, "test1");
		System.out.println("Job Instance id = "+result);
	}
}
