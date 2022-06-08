package com.woniu.k15.web;

import com.woniu.k15.entity.Permission;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.PermissionService;
import com.woniu.k15.vo.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
@Api("对菜单进行操作")
public class MenuController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/showMenus")
    @ApiOperation("查询所有菜单")
    public ResultResponse<List<MenuVo>> showMenus(){
        ResultResponse<List<MenuVo>> result = null;
        try{
            List<MenuVo> menuVos = permissionService.queryAllMenus();
            result = new ResultResponse<>(200,"ok",menuVos);
        }catch (RuntimeException e){
            result = new ResultResponse<>(404,"查询失败");
        }
        return result;
    }

    @GetMapping("/showMenuByName")
    @ApiOperation("查询所有菜单")
    public ResultResponse<List<MenuVo>> showMenuByName(String name){
        ResultResponse<List<MenuVo>> result = null;
        try{
            List<MenuVo> menuVos = permissionService.queryAllMenuByName(name);
            result = new ResultResponse<>(200,"ok",menuVos);
        }catch (RuntimeException e){
            result = new ResultResponse<>(404,"查询失败");
        }
        return result;
    }

    @GetMapping("/showParentMenus")
    @ApiOperation("查询第一级菜单")
    public ResultResponse<List<Permission>> showParentMenus(){
        ResultResponse<List<Permission>> result = null;
        try{
            List<Permission> menuVos = permissionService.queryAllParentMenus();
            result = new ResultResponse<>(200,"ok",menuVos);
        }catch (RuntimeException e){
            result = new ResultResponse<>(404,"查询失败");
        }
        return result;
    }
}
