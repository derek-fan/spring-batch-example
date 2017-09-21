package com.yucheng.cmis.batch.complex.writers.mapper;

import java.util.List;

import com.yucheng.cmis.batch.complex.writers.pojo.TCredit;


public interface TCreditMapper {
    
    List<TCredit> selectPaging();
    
    List<TCredit> selectPagingWhereList(List<Object> item);
}