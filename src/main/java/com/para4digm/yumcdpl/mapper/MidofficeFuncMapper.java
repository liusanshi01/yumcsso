package com.para4digm.yumcdpl.mapper;

import com.para4digm.yumcdpl.dto.domain.MidofficeFunc;

public interface MidofficeFuncMapper {
    int deleteByPrimaryKey(Integer funcId);

    int insert(MidofficeFunc record);

    int insertSelective(MidofficeFunc record);

    MidofficeFunc selectByPrimaryKey(Integer funcId);

    int updateByPrimaryKeySelective(MidofficeFunc record);

    int updateByPrimaryKey(MidofficeFunc record);
}