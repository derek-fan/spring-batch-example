package com.yucheng.cmis.batch.complex.reader.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * ¸´ºÏPOJOµÄ¸¸Àà
 * @author yuhq
 *
 */
public abstract class ComplextPojo {
	protected Map<String, Object> map = new HashMap<String, Object>();

	public Object getValue(String key) {
		return map.get(key);
	}
	
	public Map<String, Object> getMap() {
		return map;
	}

}
