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