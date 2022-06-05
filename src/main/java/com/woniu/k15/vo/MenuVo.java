package com.woniu.k15.vo;

import lombok.Data;

import java.util.List;
@Data
public class MenuVo {
    private int id;
    private String name;
    private String url;
    private List<MenuVo> children;
}
