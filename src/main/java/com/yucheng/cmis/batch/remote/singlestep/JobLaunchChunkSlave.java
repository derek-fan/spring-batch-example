package com.yucheng.cmis.batch.remote.singlestep;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;

import com.yucheng.cmis.batch.JobLaunchBase;


/**
 * Remote-Stepԭ��
 * 		Maste�����ݷ��͵�MQ�ϣ�Slave(Զ��)����MQ,�Ӷ�����ȡ���ݴ����������ͨ�����з��ظ�Maste
 * 
 * Slave��ʾ��:
 * 
 * 
 * ע:slave�������¼�����������ݿ⣬�������û����ڴ�Ĳֿ�
 * org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean
 * @author yuhq
 *
 */
public class JobLaunchChunkSlave{
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JobLaunchBase.executeJob("remote/singlestep/slave/job/job-chunk-slave.xml", "remoteChunkJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
