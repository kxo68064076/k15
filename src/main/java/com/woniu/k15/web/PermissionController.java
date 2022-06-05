package com.woniu.k15.web;

import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Permission;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.PermissionService;
import com.woniu.k15.util.TimerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/queryAllPermission")
    public ResultResponse<PageInfo<Permission>> queryAllPermission(String permission,
                                                                @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                                @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        ResultResponse<PageInfo<Permission>> result = null;

        try{
            PageInfo<Permission> pageInfo = permissionService.queryAllPermission(permission, pageNum, pageSize);
            result = new ResultResponse<>(200, "查询成功", pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "查询失败");
        }
        return result;
    }

    @PutMapping("/modify")
    public ResultResponse<String> modify(@RequestBody Permission permission){
        permission.setUpdateTime(new Date());
        ResultResponse<String> result = null;

        try{
            int i = permissionService.modify(permission);
            if (i==1){
                result = new ResultResponse<>(200, "修改成功");

            }else {
                result = new ResultResponse<>(201, "修改成功");

            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "修改失败");
        }

        return result;
    }

    @PutMapping("/add")
    public ResultResponse<String> add(@RequestBody Permission permission){
        permission.setInsertTime(new Date());
        ResultResponse<String> result = null;
        try{
            int i = permissionService.add(permission);
            if (i==1){
                result = new ResultResponse<>(200, "添加成功");

            }else {
                result = new ResultResponse<>(201, "添加成功");

            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "添加失败");
        }

        return result;
    }

    @DeleteMapping("/delete/{id}")
    public ResultResponse<String> delete(@PathVariable int id){
        ResultResponse<String> result = null;
        try{
            int i = permissionService.delete(id);
            if (i==1){
                result = new ResultResponse<>(200, "删除成功");

            }else {
                result = new ResultResponse<>(201, "删除成功");

            }
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "删除失败");
        }
        return result;
    }


}
