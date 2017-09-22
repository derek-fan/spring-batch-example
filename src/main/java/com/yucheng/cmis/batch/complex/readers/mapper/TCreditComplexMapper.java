package com.yucheng.cmis.batch.complex.readers.mapper;

import java.util.List;

import com.yucheng.cmis.batch.complex.readers.pojo.TCreditComplex;



public interface TCreditComplexMapper {
    
    List<TCreditComplex> selectPaging();
    
    List<TCreditComplex> selectPagingWhere(String idMin,String idMax);

}