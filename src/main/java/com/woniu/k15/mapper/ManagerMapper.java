package com.woniu.k15.mapper;

import com.woniu.k15.entity.Manager;
import com.woniu.k15.vo.ManagerVo;
import com.woniu.k15.vo.RoleVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Manager record);

    Manager selectByPrimaryKey(Long id);

    List<Manager> selectAll();

    int updateByPrimaryKey(Manager record);

    @Select("select * from t_manager where user_name = #{name}")
    Manager selectManagerByName(String name);

    List<ManagerVo> selectMangerWithRoleByName(String name);

    @Delete("DELETE FROM t_manager_role WHERE manager_id = #{id}")
    int deleteManagerAndRoleByManagerId(Long id);


    int insertManagerAndRole(@Param("mid") Long id,@Param("ids") List<Long> ids);

    int updateByKey(ManagerVo managerVo);
}