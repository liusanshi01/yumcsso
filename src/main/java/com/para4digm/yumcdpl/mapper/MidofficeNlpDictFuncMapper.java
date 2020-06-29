package com.para4digm.yumcdpl.mapper;

import com.para4digm.yumcdpl.dto.domain.MidofficeNlpDictFunc;

public interface MidofficeNlpDictFuncMapper {
    int deleteByPrimaryKey(Integer dictId);

    int insert(MidofficeNlpDictFunc record);

    int insertSelective(MidofficeNlpDictFunc record);

    MidofficeNlpDictFunc selectByPrimaryKey(Integer dictId);

    int updateByPrimaryKeySelective(MidofficeNlpDictFunc record);

    int updateByPrimaryKey(MidofficeNlpDictFunc record);
}