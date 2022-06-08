package com.woniu.k15.mapper;

import com.woniu.k15.entity.Permission;
import com.woniu.k15.vo.MenuVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll(String permission);

    List<Permission> select();

    int updateByPrimaryKey(Permission record);

    List<MenuVo> selectAllMenus();

    List<MenuVo> selectAllMenuByName(String name);

    List<Permission> selectAllParentMenus();
    //根据权限ID删除角色和权限的中间表数据
    @Delete("delete from t_role_url_permission where url_permission_id = #{id}")
    int deleteRoleAndPermissionByPerId(long id);
}