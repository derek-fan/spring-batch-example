package com.yucheng.cmis.batch.partitioner.partitioning;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 分区策略:
 * 根据T_CREDIT表主键ID，如果分区为N，则ID按N等份分划分
 * 
 * @author yuhq
 *
 */
public class DBWithAutoIncrementIDPartitStrategy implements Partitioner{
	Logger logger = Logger.getLogger(DBWithAutoIncrementIDPartitStrategy.class);
	private static final String _MINRECORD = "_minRecord";
	private static final String _MAXRECORD = "_maxRecord";
	private static final String MIN_SELECT_PATTERN = "select min({0}) from {1}";
	private static final String MAX_SELECT_PATTERN = "select max({0}) from {1}";
	private JdbcTemplate jdbcTemplate ;
	private DataSource dataSource;
	private String table ;
	private String column;
	
	/**
	 * 分区策略主方法
	 * 
	 * @param gridSize 分区的个数
	 */
	public Map<String, ExecutionContext> partition(int gridSize) {
		validateAndInit();
		Map<String, ExecutionContext> resultMap = new HashMap<String, ExecutionContext>();
		
		String minSql = MessageFormat.format(MIN_SELECT_PATTERN, new Object[]{column,table});
		String maxSql = MessageFormat.format(MAX_SELECT_PATTERN, new Object[]{column,table});
		
		Integer min = jdbcTemplate.queryForObject(minSql, Integer.class);//该表ID的最小值 
		Integer max = jdbcTemplate.queryForObject(maxSql, Integer.class);//该表ID的最大值
		
		int eveAvg = max/gridSize; 
		
		int gridMax = 0; 
		for (int i = 0; i < gridSize; i++) {
			ExecutionContext context = new ExecutionContext();
			gridMax = min+eveAvg;
			if(gridMax > max)
				gridMax = max;
			
			//将切分后的最大、最小值放到context中，以便ItemReader过滤对应分区数据
			context.putInt(_MINRECORD, min);
			context.putInt(_MAXRECORD, gridMax);
			
			min = gridMax+1;
			
			resultMap.put("partition"+(i), context);
		}
		
		logger.log(Priority.INFO, "分区策略："+resultMap);
		
		return resultMap;
	}
	
	
	public static void main(String[] args) {
		int min = 1;
		int max = 1000;
		int count = 1000; 
		int gridSize = 3;
		System.out.println(count/gridSize);
		
		int a = count/gridSize;
		
		int gridmax = 0;
		for (int i = 0; i < 3; i++) {
			gridmax = min+a;
			System.out.println("max="+gridmax);
			System.out.println(gridmax>max);
			if(gridmax>max)
				gridmax = max;
			System.out.println("min = "+min +", max = "+gridmax);
			min = gridmax+1;
			
		}
		
	}
	
	public void validateAndInit(){
		if(isEmpty(table)){
			throw new IllegalArgumentException("table cannot be null");
		}
		if(isEmpty(column)){
			throw new IllegalArgumentException("column cannot be null");
		}
		if(dataSource!=null && jdbcTemplate==null){
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		if(jdbcTemplate==null){
			throw new IllegalArgumentException("jdbcTemplate cannot be null");
		}
	}

	public static boolean isEmpty(String info){
		if(info!=null){
			if(info.trim().length()>1){
				return false;
			}
		}
		return true;
	}
	
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
}

