package com.yucheng.cmis.batch.partitioner.partitioning;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

class PartitionStepExecutionListener implements StepExecutionListener{

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("ThreadName="+Thread.currentThread().getName()+
				", StepName="+stepExecution.getStepName());
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
