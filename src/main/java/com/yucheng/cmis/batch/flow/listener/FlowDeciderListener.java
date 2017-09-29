package com.yucheng.cmis.batch.flow.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

public class FlowDeciderListener extends StepExecutionListenerSupport{

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//通过JobExecution来获取启动时赋值的参数:flowDeciderType，生效范围为当前Job
		String flowDeciderType = stepExecution.getJobExecution().getJobParameters().getString("flowDeciderType");
		
		//通过stepExecution可以获取Step中赋予的临时变量，生效范围为当前Step
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