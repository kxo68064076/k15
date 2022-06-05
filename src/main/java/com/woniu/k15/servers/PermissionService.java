package com.woniu.k15.servers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Permission;
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

    public List<Permission> queryAllParentMenus(){
        return permissionMapper.selectAllParentMenus();
    }

    public PageInfo<Permission> queryAllPermission(String permission, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Permission> permissions = permissionMapper.selectAll(permission);
        return new PageInfo<>(permissions);
    }

    public int modify(Permission permission){
        int i = permissionMapper.updateByPrimaryKey(permission);
        return i;
    }

    public int add(Permission permission){
        int i = permissionMapper.insert(permission);
        return i;
    }

    public int delete(int id){
        int i = permissionMapper.deleteByPrimaryKey((long)id);
        return i;
    }


}
