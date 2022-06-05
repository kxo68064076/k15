package com.woniu.k15.servers;

import com.woniu.k15.entity.Manager;
import com.woniu.k15.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServers {
    @Autowired
    ManagerMapper managerMapper;

    public Manager login(String name){
        return managerMapper.selectManagerByName(name);
    }
}
