## ����ʾ��˵��

### INIT
/test/resources/db��ʾ�����ڳ�ʼ�����ݿ�Ľű�

### SpringBatch����MyBatisʾ��
��ڣ�/batch/mybatis/JobLaunchMybatis.java


### Step����
- ��ڣ�/batch/partitioner/JobLaunchPartitioner.java
- ������`SpringBatch����MyBatisʾ��`

### Զ��Step
- Master��ڣ�/batch/remote/singlestep/JobLaunchChunkMaste.java
- Slave��ڣ�/batch/remote/singlestep/JobLaunchChunkSlave.java
- ������`SpringBatch����MyBatisʾ��`