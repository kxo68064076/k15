package com.woniu.k15.mapper;

import com.woniu.k15.entity.Role;
import com.woniu.k15.vo.RoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    @Delete("delete from t_role_url_permission where role_id = #{id}")
    int deleteRoleAndPermissionByPerId(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    List<Role> selectByName(String name);

    int updateByPrimaryKey(Role record);

    List<Long> selectRoleVoByKey(Long id);

    void addBatchRoleAndPer(@Param("id") long id, @Param("checkedIds") List<Long> checkedIds);

    List<Role> selectRolesBymid(Long id);
}