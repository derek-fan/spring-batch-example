package com.yucheng.cmis.batch.remote.partitioningstep.rpw;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class SamplePartition implements Partitioner {
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		Map<String, ExecutionContext> resultMap = new HashMap<String, ExecutionContext>();

		for (int i = 0; i < gridSize; i++) {
			ExecutionContext context = new ExecutionContext();
			context.put("_currentPart", i);
			resultMap.put("part-" + (i + 1), context);
		}
		System.out.println("===="+resultMap);
		return resultMap;
	}

}
