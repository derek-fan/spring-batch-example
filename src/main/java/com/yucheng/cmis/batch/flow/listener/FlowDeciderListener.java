package com.yucheng.cmis.batch.flow.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class FlowDeciderListener extends StepExecutionListenerSupport{

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//ͨ��JobExecution����ȡ����ʱ��ֵ�Ĳ���:flowDeciderType����Ч��ΧΪ��ǰJob
		String flowDeciderType = stepExecution.getJobExecution().getJobParameters().getString("flowDeciderType");
		
		//ͨ��stepExecution���Ի�ȡStep�и������ʱ��������Ч��ΧΪ��ǰStep
		//stepExecution.getExecutionContext().getString("testStepParame");
		
		if(flowDeciderType == null || !flowDeciderType.equals("true"))
			return new ExitStatus("T_FAILD");
		else 
			return new ExitStatus("XXX");
	}
}