package com.woniu.k15.mapper;

import com.woniu.k15.entity.Remark;
import java.util.List;

public interface RemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Remark record);

    Remark selectByPrimaryKey(Integer id);

    List<Remark> selectAll();

    int updateByPrimaryKey(Remark record);
}