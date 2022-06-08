package com.woniu.k15.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(value = "handler")
public class ManagerVo {

    private int id;
    private String userCode;
    private String userName;
    private String cellphone;
    private String email;
    private String locked;
    private List<RoleVo> roleList;

}
