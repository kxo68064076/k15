package com.woniu.k15.servers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Role;
import com.woniu.k15.mapper.RoleMapper;
import com.woniu.k15.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;


    public PageInfo<Role> queryByName(String role, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Role> permissions = roleMapper.selectByName(role);
        return new PageInfo<>(permissions);
    }

    public int modify(Role role){
        int i = roleMapper.updateByPrimaryKey(role);
        return i;
    }

    public int add(Role role){
        int i = roleMapper.insert(role);
        return i;
    }
    @Transactional
    public int delete(int id){
        roleMapper.deleteRoleAndPermissionByPerId((long)id);
        int i = roleMapper.deleteByPrimaryKey((long)id);
        return i;
    }

    public List<Long> queryCheckedIds(Long id){
        return roleMapper.selectRoleVoByKey(id);
    }

    @Transactional
    public int rolePer(RoleVo roleVo){
        roleMapper.deleteRoleAndPermissionByPerId(roleVo.getId());
        roleMapper.addBatchRoleAndPer(roleVo.getId(),roleVo.getCheckedIds());
        return 0;
    }


    public List<Role> queryAll() {
        return roleMapper.selectAll();
    }
}
