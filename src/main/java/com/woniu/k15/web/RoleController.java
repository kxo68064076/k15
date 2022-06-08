package com.woniu.k15.web;

import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Role;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.RoleService;
import com.woniu.k15.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/queryByName")
    public ResultResponse<PageInfo<Role>> queryByName(String name,
                                                             @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                             @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){
        ResultResponse<PageInfo<Role>> result = null;

        try{
            PageInfo<Role> pageInfo = roleService.queryByName(name, pageNum, pageSize);
            result = new ResultResponse<>(200, "查询成功", pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "查询失败");
        }
        return result;
    }

    @GetMapping("/queryAll")
    public ResultResponse<List<Role>> queryAll(){
        ResultResponse<List<Role>> result = null;

        try{
            List<Role> roles = roleService.queryAll();
            result = new ResultResponse<>(200, "查询成功",roles);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "查询失败");
        }
        return result;
    }

    @PutMapping("/modify")
    public ResultResponse<String> modify(@RequestBody Role role){
        role.setUpdateTime(new Date());
        ResultResponse<String> result = null;

        try{
            int i = roleService.modify(role);
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
    public ResultResponse<String> add(@RequestBody Role role){
        role.setInsertTime(new Date());
        ResultResponse<String> result = null;
        try{
            int i = roleService.add(role);
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
            int i = roleService.delete(id);
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

    @GetMapping("/queryCheckedIds/{id}")
    public ResultResponse<List<Long>> queryCheckedIds(@PathVariable Long id){
        ResultResponse<List<Long>> result = null;

        try{
            List<Long> ids = roleService.queryCheckedIds(id);
            result = new ResultResponse<>(200, "查询成功", ids);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "查询失败");
        }
        return result;
    }

    @PostMapping("/rolePer")
    public ResultResponse<List<Long>> rolePer(@RequestBody RoleVo roleVo){
        ResultResponse<List<Long>> result = null;

        try{
            roleService.rolePer(roleVo);
            result = new ResultResponse<>(200, "角色授权成功");
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "角色授权失败");
        }
        return result;

    }


}
