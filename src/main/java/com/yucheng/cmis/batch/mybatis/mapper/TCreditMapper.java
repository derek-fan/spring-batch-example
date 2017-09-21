package com.yucheng.cmis.batch.mybatis.mapper;

import java.util.List;

import com.yucheng.cmis.batch.mybatis.pojo.TCredit;


public interface TCreditMapper {
    int deleteByPrimaryKey(String id);

    int insert(TCredit record);

    int insertSelective(TCredit record);

    TCredit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TCredit record);

    int updateByPrimaryKey(TCredit record);
    
    List<TCredit> selectPaging();
    
    List<TCredit> selectPagingWhere(String idMin,String idMax);

}