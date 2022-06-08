package com.woniu.k15.servers;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Manager;
import com.woniu.k15.mapper.ManagerMapper;
import com.woniu.k15.vo.ManagerVo;
import com.woniu.k15.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManagerServers {

    @Autowired
    ManagerMapper managerMapper;

    public Manager login(String name){
        return managerMapper.selectManagerByName(name);
    }

    public PageInfo<ManagerVo> showAllManagerByName(String name,int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ManagerVo> managerVos = managerMapper.selectMangerWithRoleByName(name);
        return new PageInfo<>(managerVos);
    }

    @Transactional
    public int modify(ManagerVo managerVo) {
        managerMapper.deleteManagerAndRoleByManagerId((long)managerVo.getId());
        ArrayList<Long> ids = new ArrayList<>();
        for (RoleVo roleVo : managerVo.getRoleList()){
            ids.add(roleVo.getId());
        }
        managerMapper.insertManagerAndRole((long)managerVo.getId(),ids);
        int i = managerMapper.updateByKey(managerVo);
        return i;
    }

    public int modifyManagerInfo(Manager manager) {
        int i = managerMapper.updateByPrimaryKey(manager);
        return i;
    }

    public Manager findManagerById(int id) {
        return managerMapper.selectByPrimaryKey((long)id);
    }
}
