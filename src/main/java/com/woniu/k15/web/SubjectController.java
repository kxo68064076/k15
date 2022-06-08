package com.woniu.k15.web;

import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Manager;
import com.woniu.k15.entity.Subject;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.ManagerServers;
import com.woniu.k15.servers.SubjectService;
import com.woniu.k15.util.JwtUtil;
import com.woniu.k15.vo.AccountVo;
import com.woniu.k15.vo.JwtInfo;
import com.woniu.k15.vo.ManagerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subject")
@Api("学科")
public class SubjectController {

    @Autowired
    SubjectService subjectService;
    /**
     *
     * @return
     */
    @GetMapping("/slist")
    @ApiOperation("查询全部科目")
    public ResultResponse<List<Subject>> slist(){
        ResultResponse<List<Subject>> result = null;
        try{
            List<Subject> subjects = subjectService.queryAllSubject();
            result = new ResultResponse<>(200, "学科查询成功",subjects);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "学科查询失败");
        }
        return result;
    }
}
