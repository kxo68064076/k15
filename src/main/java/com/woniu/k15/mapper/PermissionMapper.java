package com.woniu.k15.mapper;

import com.woniu.k15.entity.Permission;
import com.woniu.k15.vo.MenuVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll(String permission);

    int updateByPrimaryKey(Permission record);

    List<MenuVo> selectAllMenus();

    List<Permission> selectAllParentMenus();
}