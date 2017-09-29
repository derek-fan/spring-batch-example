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

### 组合读
- 一次性读多张表，适用主表+子表+从表
- 入口:com.yucheng.cmis.batch.complex.readers.JobLaunchComplextReader
- 核心配置:

```xml
<!-- 读取数据 -->
<bean id="complexReaders" class="com.yucheng.cmis.batch.complex.readers.reader.MyBatisPagingMuiltItemReader" scope="step">
	<property name="sqlSessionFactory" ref="sqlSessionFactory" />
	<!-- 主查询ID -->
	<property name="queryId" value="com.yucheng.cmis.batch.complex.readers.mapper.TCreditComplexMapper.selectPaging" />
	<!-- 分页大小 -->
	<property name="pageSize" value="10" />
	<!-- 子查询需要的额外参数 -->
	<property name="parameterValues">
		<map>
			<entry key="mytestParam" value="#{jobParameters['_myTestParam']}" />
		</map>
	</property>
	<!-- 子查询：读取数据配置，支持多个 -->
	<property name="subReaders">
		<list>
			<ref bean="subReader1" />
		</list>
	</property>
</bean>

<!-- 子查询 -->
<bean id="subReader1" class="com.yucheng.cmis.batch.complex.readers.reader.MyBatisSubItemReader" scope="prototype">
	<!-- 子查询ID -->
	<property name="queryId" value="com.yucheng.cmis.batch.common.mapper.TTradeRecordMapper.selectWithSubReader" />
	<!-- 主表的主键 -->
	<property name="fKField" value="id" />
	<!-- POJO中属性，用于子查询将结果集set到pojo中 -->
	<property name="pField" value="ttradeRecordList" />
	<!-- true:主表每次查询的时候都会触发子表查询；false:子表查询只会触发一次 -->
	<property name="oneByOne" value="true" />
</bean>
```

### Job流程示例
#### 顺序流程
- 入口：com.yucheng.cmis.batch.flow.JobLaunchFlowSequential

### 条流流程
- 入口:com.yucheng.cmis.batch.flow.JobLaunchFlowCondition，根据启动时传入的参数来判断流程走向
- 核心配置：监听，通过监听的afterStep来判断并设置ExitStatus; next标签根据on做条件匹配，to指向下一步骤

### 参数传递示例
- 入口:com.yucheng.cmis.batch.paramers.JobLaunchParamers
- 演示了设置Job参数，XML中获取Job参数；Step参数设置及使用
