package com.woniu.k15;

import com.woniu.k15.entity.Permission;
import com.woniu.k15.mapper.PermissionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class K15ApplicationTests {
    @Autowired
    PermissionMapper permissionMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void test01(){
        List<Permission> permissions = permissionMapper.selectAllParentMenus();
        for (Permission permission : permissions){
            System.out.println(permission);
        }
    }

}
