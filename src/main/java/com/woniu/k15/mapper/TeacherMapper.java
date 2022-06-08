package com.woniu.k15.mapper;

import com.woniu.k15.entity.Teacher;
import com.woniu.k15.vo.TeacherVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    List<Teacher> selectAll();

    List<TeacherVo> selectAllByName(String tname);

    int updateByPrimaryKey(Teacher record);
}