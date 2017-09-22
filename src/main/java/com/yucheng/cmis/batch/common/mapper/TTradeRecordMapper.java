package com.yucheng.cmis.batch.common.mapper;

import java.util.List;

import com.yucheng.cmis.batch.common.pojo.TTradeRecord;


public interface TTradeRecordMapper {
    
    TTradeRecord selectByPrimaryKey(int id);
    
    List<TTradeRecord> selectWithSubReader(int id);
}