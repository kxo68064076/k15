package com.woniu.k15.vo;

import com.woniu.k15.entity.Subject;
import lombok.Data;

@Data
public class TeacherVo {
    private Integer id;

    private String name;

    private String education;

    private String career;

    private String isfamous;

    private String avatar;

    private Integer subjectid;

    private Subject subject;

    private String status;
}
