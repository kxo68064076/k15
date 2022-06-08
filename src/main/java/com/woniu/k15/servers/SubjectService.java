package com.woniu.k15.servers;

import com.woniu.k15.entity.Subject;
import com.woniu.k15.mapper.SubjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    SubjectMapper subjectMapper;

    public List<Subject> queryAllSubject(){
        return subjectMapper.selectAll();
    }
}
