package com.yucheng.cmis.batch.complex.writers.mapper;

import java.util.List;

import com.yucheng.cmis.batch.common.pojo.TCredit;


public interface TCreditMapper {
    
    List<TCredit> selectPaging();
    
    List<TCredit> selectPagingWhereList(List<Object> item);
}