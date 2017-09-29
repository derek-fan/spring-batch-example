package com.yucheng.cmis.batch.flow.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

public class FlowDeciderListener extends StepExecutionListenerSupport{

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//ͨ��JobExecution����ȡ����ʱ��ֵ�Ĳ���:flowDeciderType����Ч��ΧΪ��ǰJob
		String flowDeciderType = stepExecution.getJobExecution().getJobParameters().getString("flowDeciderType");
		
		//ͨ��stepExecution���Ի�ȡStep�и������ʱ��������Ч��ΧΪ��ǰStep
		//stepExecution.getExecutionContext().getString("testStepParame");
		ExecutionContext context = stepExecution.getExecutionContext();
		context.put("dd", "sssssssssssddd");
		
		System.out.println(context);
		
		if(flowDeciderType == null || !flowDeciderType.equals("true"))
			return new ExitStatus("T_FAILD");
		else 
			return new ExitStatus("XXX");
	}
}