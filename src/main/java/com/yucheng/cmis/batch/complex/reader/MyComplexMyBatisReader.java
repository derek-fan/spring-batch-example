package com.yucheng.cmis.batch.complex.reader;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.batch.MyBatisPagingItemReader;

import com.yucheng.cmis.batch.common.pojo.TCredit;
import com.yucheng.cmis.batch.complex.reader.pojo.ComplextPojo;

/**
 * 
 * T����Ϊ���������processor
 * 
 *    ��״��processor����Ҫ��ѯ12�����ݿ⣬��Ϊ��Ҫ��ѯ�����ݺܶ���һ�Զ࣬������������reader�С� processor�еĲ�ѯ�������࣬����SpringBatch��chunk������processor�еĲ�ѯҲ�������ģ�
 * ����Ӧ�����нϴ�����������Reader�������ò�ѯ��һҳ���ݣ���ҳ��ѯ���󣬽���ҳ���ݼ�processor����Ҫ������һ�β�ѯ��,�����ҳ������100����ԭ����processor��һ��Ҫִ��12*100�β�ѯ������ֻҪִ��12
 * �β�ѯ��
 * 
 *    ��дMyBatis��Reader�������ﵽ������mybatisReader�����ö�ȡ�������ݣ�����ҵ�����ݣ��� ��Reader�������ҵ�����ݵ���������ѯprocessor����Ҫ�����ݡ���ô��Ҫ����һ�����϶��󣬸ø��϶���������
 * �ӱ��ӱ������
 * 
 * @author yuhq
 *
 * @param <T>
 */
public class MyComplexMyBatisReader<T> extends MyBatisPagingItemReader<T>{
	private String keyName;
	private String subQueryId;
	

	@Override
	protected void doReadPage() {
		// �����Ѿ������õ���SQL�鿴���
		super.doReadPage();
		// ���������ݽ��������ȡ��SQL��where��ѯ����
		List<Object> paramList = getSubSqlParameters();
		
		if(paramList.size()==0)
			return;
		
		List<TCredit> list =  sqlSessionTemplate.<TCredit> selectList(subQueryId, paramList);
		System.out.println(list.size());
		
	}
	
	/**
	 * ���������ݽ��������ȡ��SQL��where��ѯ����
	 * 
	 * @return
	 */
	protected List<Object> getSubSqlParameters(){
		List<Object> paramList = new ArrayList<Object>();
		
		//results��mybatis��ѯ����SQL���ݼ�
		for (T t : results) {
			if(t instanceof ComplextPojo) {
				paramList.add(((ComplextPojo) t).getValue(keyName));
			}
		}
		
		return paramList;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getSubQueryId() {
		return subQueryId;
	}

	public void setSubQueryId(String subQueryId) {
		this.subQueryId = subQueryId;
	}
	
}
