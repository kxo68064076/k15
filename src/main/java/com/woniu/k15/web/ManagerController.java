package com.woniu.k15.web;

import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Manager;
import com.woniu.k15.entity.Permission;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.ManagerServers;
import com.woniu.k15.util.JwtUtil;
import com.woniu.k15.vo.AccountVo;
import com.woniu.k15.vo.JwtInfo;
import com.woniu.k15.vo.ManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("manager")
@Api("管理员的操作")
public class ManagerController {

    @Autowired
    ManagerServers managerServers;
    /**
     *
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

    @GetMapping("/showAllManagerByName")
    public ResultResponse<PageInfo<ManagerVo>> showAllManagerByName(String userName,
                                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                                @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        ResultResponse<PageInfo<ManagerVo>> result = null;
        try{
            PageInfo<ManagerVo> managerVoPageInfo = managerServers.showAllManagerByName(userName, pageNum, pageSize);
            result = new ResultResponse<>(200, "查询成功", managerVoPageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "查询失败");
        }
        return result;
    }

    @PutMapping("/modify")
    public ResultResponse<String> modify(@RequestBody ManagerVo managerVo){

        ResultResponse<String> result = null;
        Manager manager = managerServers.findManagerById(managerVo.getId());
        manager.setUserCode(managerVo.getUserCode());
        manager.setUserName(managerVo.getUserName());
        manager.setCellphone(managerVo.getCellphone());
        manager.setEmail(managerVo.getEmail());
        manager.setLocked(managerVo.getLocked());
        manager.setUpdateTime(new Date());
        try{
            int i = managerServers.modify(managerVo);
            managerServers.modifyManagerInfo(manager);
            if (i==1){
                result = new ResultResponse<>(200, "修改成功");

            }else {
                result = new ResultResponse<>(201, "修改失败");

            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "修改失败");
        }

        return result;
    }
}
