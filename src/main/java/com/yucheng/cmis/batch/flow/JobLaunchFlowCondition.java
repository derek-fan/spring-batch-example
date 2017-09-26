package com.yucheng.cmis.batch.flow;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * ����Flow
 * @author yuhq
 *
 */
public class JobLaunchFlowCondition{
	
	public static void launchJobWithContidion(String flag) {
		JobLaunchBase.executeJob("flow/job/job-flow-condition.xml", "conditionFlowJog",
				new JobParametersBuilder().addDate("date", new Date()).addString("flowDeciderType", flag));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//��step1->step2->step3�����ݴ����flowDeciderType��������������
		JobLaunchFlowCondition.launchJobWithContidion("true");
		
		//��step1->other
		JobLaunchFlowCondition.launchJobWithContidion("false");
	}
}