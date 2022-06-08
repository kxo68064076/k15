package com.woniu.k15.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleVo {
    private Long id;
    private String name;
    private List<Long> checkedIds;
}
