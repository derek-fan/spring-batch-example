package com.yucheng.cmis.batch.remote.singlestep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Stepԭ��
 * 		Maste�����ݷ��͵�MQ�ϣ�Slave(Զ��)����MQ,�Ӷ�����ȡ���ݴ����������ͨ�����з��ظ�Maste
 * 
 * Maste��ʾ��:
 * 
 * 
 * @author yuhq
 *
 */
public class JobLaunchChunkMaste {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("remote/singlestep/maste/job/job-chunk-maste.xml", "masteChunkJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
