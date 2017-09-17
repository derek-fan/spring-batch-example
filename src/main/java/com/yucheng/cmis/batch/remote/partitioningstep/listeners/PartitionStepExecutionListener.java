package com.yucheng.cmis.batch.remote.partitioningstep.listeners;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;

public class PartitionStepExecutionListener implements StepExecutionListener {
	int i=0;

	public void beforeStep(StepExecution stepExecution) {
		System.out.println("ThreadName=" + Thread.currentThread().getName() + "; " 
				+ "StepName=" + stepExecution.getStepName() + ";");
		
		ExecutionContext context = stepExecution.getExecutionContext();
		System.out.println("分区标识："+context);
		
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}
}
