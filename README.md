## 开发示例说明

### INIT
/test/resources/db是示例用于初始化数据库的脚本

### SpringBatch集成MyBatis示例
入口：com.yucheng.cmis.batch.mybatis.JobLaunchMybatis.java


### Step分区
- 入口：com.yucheng.cmis.batch.partitioner.JobLaunchPartitioner.java
- 依赖：`SpringBatch集成MyBatis示例`

### 远程Step
- Master入口：com.yucheng.cmis.batch.remote.singlestep.JobLaunchChunkMaste.java
- Slave入口：com.yucheng.cmis.batch.remote.singlestep.JobLaunchChunkSlave.java
- 依赖：`SpringBatch集成MyBatis示例`

### 远程分区Step
- Master入口：com.yucheng.cmis.batch.remote.partitioningstep.JobLaunchPartitionMaste
- Slave入口：com.yucheng.cmis.batch.remote.partitioningstep.JobLaunchPartitionSlave
- 依赖：`SpringBatch集成MyBatis示例`,`Step分区`的分区策略

### 组合写Step
一次性写入多张表
入口：com.yucheng.cmis.batch.complex.writers.JobLaunchComplextWriter
 核心配置：
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