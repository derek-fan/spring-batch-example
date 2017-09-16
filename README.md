## 开发示例说明

### INIT
/test/resources/db是示例用于初始化数据库的脚本

### SpringBatch集成MyBatis示例
入口：/batch/mybatis/JobLaunchMybatis.java


### Step分区
- 入口：JobLaunchPartitioner.java
- 依赖：`SpringBatch集成MyBatis示例`

### 远程Step
- Master入口：JobLaunchChunkMaste.java
- Slave入口：JobLaunchChunkSlave.java
- 依赖：`SpringBatch集成MyBatis示例`