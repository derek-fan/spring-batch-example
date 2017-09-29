package com.yucheng.cmis.batch.paramers.listener;

import java.util.Date;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;

import com.yucheng.cmis.batch.common.pojo.User;

public class Listener1 implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("--------Listener1:beforeStep------------");
		
		JobParameters parameters = stepExecution.getJobExecution().getJobParameters();
		String paramId = parameters.getString("paramId");
		Date date = parameters.getDate("date");  
		System.out.println("    Job参数：paramId=" + paramId + " , date="+date);
		
		stepExecution.getExecutionContext().put("L1.name", "监听1.张三");
		
		User user = new User();
		user.setAge(30);
		user.setName("李四");
		user.setWage(30000.99);
		stepExecution.getExecutionContext().put("L1.user", user);
		System.out.println("    StepContext设置参数:L1.name=监听1.张三 \n    user="+user.toString());
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("--------Listener1:afterStep------------");
		String c1 = stepExecution.getExecutionContext().getString("L1.name");
		User user = (User)stepExecution.getExecutionContext().get("L1.user");
		System.out.println("    StepContext设置参数:L1.name=" + c1 +"\n    user="+user.toString());
		
		return new ExitStatus("自定义");
	}

}