package com.woniu.k15.web;

import com.woniu.k15.entity.Manager;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.ManagerServers;
import com.woniu.k15.util.JwtUtil;
import com.woniu.k15.vo.AccountVo;
import com.woniu.k15.vo.JwtInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manager")
@Api("管理员的操作")
public class ManagerController {

    @Autowired
    ManagerServers managerServers;
    /**
     *
     * @param accountVo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户的登录操作")
    public ResultResponse<Manager> login(@ApiParam(name = "accountVo" , value = "接受用户名和密码")
            @RequestBody AccountVo accountVo){
        ResultResponse<Manager> result = null;

        Manager manager = managerServers.login(accountVo.getUserName());
        if (manager == null){
            result = new ResultResponse<>(201,"账户不存在。");
        }else if(manager.getUserPassword().equals(accountVo.getUserPassword())){
            result = new ResultResponse<>(200,"登录成功。");
            JwtInfo jwtInfo = new JwtInfo();
            jwtInfo.setUsername(manager.getUserName());
            String token = JwtUtil.createToken(jwtInfo);
            result.setToken(token);
        }else {
            result = new ResultResponse<>(202,"密码不正确。",manager);

        }
        return result;
    }
}
