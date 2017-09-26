package com.yucheng.cmis.batch.flow;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * 条件Flow
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
		
		//走step1->step2->step3，根据传入的flowDeciderType来决定流程走向
		JobLaunchFlowCondition.launchJobWithContidion("true");
		
		//走step1->other
		JobLaunchFlowCondition.launchJobWithContidion("false");
	}
}