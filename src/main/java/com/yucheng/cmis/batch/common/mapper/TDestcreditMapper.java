package com.yucheng.cmis.batch.common.mapper;

import com.yucheng.cmis.batch.common.pojo.TDestcredit;

public interface TDestcreditMapper {
    int deleteByPrimaryKey(String id);

    int insert(TDestcredit record);

    int insertSelective(TDestcredit record);

    TDestcredit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TDestcredit record);

    int updateByPrimaryKey(TDestcredit record);
}