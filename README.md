## ����ʾ��˵��

### INIT
/test/resources/db��ʾ�����ڳ�ʼ�����ݿ�Ľű�

### SpringBatch����MyBatisʾ��
��ڣ�com.yucheng.cmis.batch.mybatis.JobLaunchMybatis.java


### Step����
- ��ڣ�com.yucheng.cmis.batch.partitioner.JobLaunchPartitioner.java
- ������`SpringBatch����MyBatisʾ��`

### Զ��Step
- Master��ڣ�com.yucheng.cmis.batch.remote.singlestep.JobLaunchChunkMaste.java
- Slave��ڣ�com.yucheng.cmis.batch.remote.singlestep.JobLaunchChunkSlave.java
- ������`SpringBatch����MyBatisʾ��`

### Զ�̷���Step
- Master��ڣ�com.yucheng.cmis.batch.remote.partitioningstep.JobLaunchPartitionMaste
- Slave��ڣ�com.yucheng.cmis.batch.remote.partitioningstep.JobLaunchPartitionSlave
- ������`SpringBatch����MyBatisʾ��`,`Step����`�ķ�������

### ���дStep
һ����д����ű�
��ڣ�com.yucheng.cmis.batch.complex.writers.JobLaunchComplextWriter
 �������ã�
```xml
<bean id="complextWriters"
	class="org.springframework.batch.item.support.CompositeItemWriter">
	<property name="sqlSessionTemplate" ref="sqlSession"></property>
	<property name="delegates">
		<list>
			<ref bean="tdestcreditWriter" />
			<ref bean="ttradeRecordWriterAdapter" />
		</list>
	</property>
</bean>
```