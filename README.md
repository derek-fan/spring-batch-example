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

### ��϶�
һ���Զ����ű���������+�ӱ�+�ӱ�
���:com.yucheng.cmis.batch.complex.readers.JobLaunchComplextReader
��������:
```xml
<!-- ��ȡ���� -->
<bean id="complexReaders" class="com.yucheng.cmis.batch.complex.readers.reader.MyBatisPagingMuiltItemReader" scope="step">
	<property name="sqlSessionFactory" ref="sqlSessionFactory" />
	<!-- ����ѯID -->
	<property name="queryId" value="com.yucheng.cmis.batch.complex.readers.mapper.TCreditComplexMapper.selectPaging" />
	<!-- ��ҳ��С -->
	<property name="pageSize" value="10" />
	<!-- �Ӳ�ѯ��Ҫ�Ķ������ -->
	<property name="parameterValues">
		<map>
			<entry key="mytestParam" value="#{jobParameters['_myTestParam']}" />
		</map>
	</property>
	<!-- �Ӳ�ѯ����ȡ�������ã�֧�ֶ�� -->
	<property name="subReaders">
		<list>
			<ref bean="subReader1" />
		</list>
	</property>
</bean>

<!-- �Ӳ�ѯ -->
<bean id="subReader1" class="com.yucheng.cmis.batch.complex.readers.reader.MyBatisSubItemReader" scope="prototype">
	<!-- �Ӳ�ѯID -->
	<property name="queryId" value="com.yucheng.cmis.batch.common.mapper.TTradeRecordMapper.selectWithSubReader" />
	<!-- ��������� -->
	<property name="fKField" value="id" />
	<!-- POJO�����ԣ������Ӳ�ѯ�������set��pojo�� -->
	<property name="pField" value="ttradeRecordList" />
	<!-- true:����ÿ�β�ѯ��ʱ�򶼻ᴥ���ӱ��ѯ��false:�ӱ��ѯֻ�ᴥ��һ�� -->
	<property name="oneByOne" value="true" />
</bean>
```