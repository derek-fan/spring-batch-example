package com.yucheng.cmis.batch.flow.decider;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * ���ݴ���Ĳ������ж��ߵ��ĸ���֧
 * @author yuhq
 *
 */
public class TestParamerDecider implements JobExecutionDecider{

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
		String flowDeciderType = jobExecution.getJobParameters().getString("flowDeciderType");
		if(flowDeciderType!=null && flowDeciderType.equals("true")) {
			return new FlowExecutionStatus("TEST_FLOW_DECIDER_TRUE");
		}else
			return new FlowExecutionStatus("TEST_FLOW_DECIDER_TRUE");
	}

}
