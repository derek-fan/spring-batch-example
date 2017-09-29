package com.yucheng.cmis.batch.paramers.listener;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;

import com.yucheng.cmis.batch.common.pojo.User;

public class Listener2 extends StepExecutionListenerSupport{

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("--------Listener2:beforeStep------------");
		String c1 = stepExecution.getExecutionContext().getString("L1.name");
		User user = (User)stepExecution.getExecutionContext().get("L1.user");
		System.out.println("    StepContext设置参数:L1.name=" + c1 +"\n    user="+user.toString());
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("--------Listener2:afterStep------------");
		String c1 = stepExecution.getExecutionContext().getString("L1.name");
		User user = (User)stepExecution.getExecutionContext().get("L1.user");
		System.out.println("    StepContext设置参数:L1.name=" + c1 +"\n    user="+user.toString());
		
		return new ExitStatus("自定义");
	}
}