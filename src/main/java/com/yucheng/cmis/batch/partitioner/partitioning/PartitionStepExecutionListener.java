package com.yucheng.cmis.batch.partitioner.partitioning;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PartitionStepExecutionListener implements StepExecutionListener {

	public void beforeStep(StepExecution stepExecution) {
		System.out.println("ThreadName=" + Thread.currentThread().getName() + "; " 
				+ "StepName=" + stepExecution.getStepName() + ";");
	}

	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}
}
