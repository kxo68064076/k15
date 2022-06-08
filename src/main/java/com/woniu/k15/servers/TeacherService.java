package com.woniu.k15.servers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Teacher;
import com.woniu.k15.mapper.TeacherMapper;
import com.woniu.k15.vo.TeacherVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    public PageInfo<TeacherVo> queryAllTeacher(String tname, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<TeacherVo> teachers = teacherMapper.selectAllByName(tname);
        return new PageInfo<>(teachers);
    }

    public Teacher queryTeacherById(Integer id){
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        return teacher;
    }

    public int modify(Teacher teacher) {
        return teacherMapper.updateByPrimaryKey(teacher);
    }

    public int add(Teacher teacher) {
        return teacherMapper.insert(teacher);
    }

    @Transactional
    public int delete(int id) {
        int i = teacherMapper.deleteByPrimaryKey(id);
        return i;
    }
}
