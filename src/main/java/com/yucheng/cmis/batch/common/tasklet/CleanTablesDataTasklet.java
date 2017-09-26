package com.yucheng.cmis.batch.common.tasklet;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * 清理指定表的数据
 * @author yuhq
 *
 */
public class CleanTablesDataTasklet implements Tasklet{
	protected final Log logger = LogFactory.getLog(getClass());
	private SqlSessionFactory sqlSessionFactory;
	//待清理的SQL
	private List<String> statementIdList = null;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		SqlSession sqlSession = null;
		long beginTime = System.currentTimeMillis();
		sqlSession = sqlSessionFactory.openSession();
		
		//执行更新操作
		for (String statementId : statementIdList) {
			sqlSession.update(statementId, null);
		}
		String[] ids = new String[statementIdList.size()];
		statementIdList.toArray(ids);
		long endTime = System.currentTimeMillis();
		logger.info("-------更新了"+ids+",耗时 " + (endTime - beginTime) + " ms");
		sqlSession.commit();
		
		return RepeatStatus.FINISHED;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public List<String> getStatementIdList() {
		return statementIdList;
	}

	public void setStatementIdList(List<String> statementIdList) {
		this.statementIdList = statementIdList;
	}

}
