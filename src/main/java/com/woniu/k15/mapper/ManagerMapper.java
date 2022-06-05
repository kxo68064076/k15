package com.woniu.k15.mapper;

import com.woniu.k15.entity.Manager;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Manager record);

    Manager selectByPrimaryKey(Long id);

    List<Manager> selectAll();

    int updateByPrimaryKey(Manager record);

    @Select("select * from t_manager where user_name = #{name}")
    Manager selectManagerByName(String name);
}