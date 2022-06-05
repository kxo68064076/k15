package com.woniu.k15.servers;

import com.woniu.k15.mapper.PermissionMapper;
import com.woniu.k15.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionMapper permissionMapper;
    public List<MenuVo> queryAllMenus(){
        return permissionMapper.selectAllMenus();
    }
}
