package com.woniu.k15.mapper;

import com.woniu.k15.entity.Lesson;
import java.util.List;

public interface LessonMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Lesson record);

    Lesson selectByPrimaryKey(Integer id);

    List<Lesson> selectAll();

    int updateByPrimaryKey(Lesson record);
}