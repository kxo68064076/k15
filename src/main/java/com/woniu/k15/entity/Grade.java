package com.woniu.k15.entity;

public class Grade {
    private Integer id;

    private String name;

    private Integer coursenum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCoursenum() {
        return coursenum;
    }

    public void setCoursenum(Integer coursenum) {
        this.coursenum = coursenum;
    }
}