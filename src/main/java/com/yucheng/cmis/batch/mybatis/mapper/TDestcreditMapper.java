package com.yucheng.cmis.batch.mybatis.mapper;

import com.yucheng.cmis.batch.mybatis.pojo.TDestcredit;

public interface TDestcreditMapper {
    int deleteByPrimaryKey(String id);

    int insert(TDestcredit record);

    int insertSelective(TDestcredit record);

    TDestcredit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TDestcredit record);

    int updateByPrimaryKey(TDestcredit record);
}