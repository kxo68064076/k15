package com.woniu.k15.web;

import com.github.pagehelper.PageInfo;
import com.woniu.k15.entity.Permission;
import com.woniu.k15.entity.Teacher;
import com.woniu.k15.result.ResultResponse;
import com.woniu.k15.servers.TeacherService;
import com.woniu.k15.vo.TeacherVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("teacher")
@Slf4j
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Value("${k15.teacher}")
    String path;

    @GetMapping("/queryAllTeacher")
    public ResultResponse<PageInfo<TeacherVo>> queryAllTeacher(String tname,
                                                           @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                           @RequestParam(value = "pageSize",defaultValue = "5") int pageSize){

        ResultResponse<PageInfo<TeacherVo>> result = null;
        try{
            PageInfo<TeacherVo> pageInfo = teacherService.queryAllTeacher(tname, pageNum, pageSize);
            result = new ResultResponse<>(200, "查询成功", pageInfo);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(404, "查询失败");
        }
        return result;
    }

    @PutMapping("/modify")
    public ResultResponse<String> modify(@RequestBody TeacherVo teacherVo){
        ResultResponse<String> result = null;
        Teacher teacher = teacherService.queryTeacherById(teacherVo.getId());
        teacher.setSubjectid(teacherVo.getSubject().getId());
        teacher.setName(teacherVo.getName());
        teacher.setEducation(teacherVo.getEducation());
        teacher.setCareer(teacherVo.getCareer());
        teacher.setIsfamous(teacherVo.getIsfamous());
        teacher.setAvatar(teacherVo.getAvatar());
        teacher.setStatus(teacherVo.getStatus());
        try{
            int i = teacherService.modify(teacher);
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
    public ResultResponse<String> add(@RequestBody TeacherVo teacherVo){
        ResultResponse<String> result = null;
        Teacher teacher = new Teacher();
        teacher.setName(teacherVo.getName());
        teacher.setSubjectid(teacherVo.getSubject().getId());
        teacher.setEducation(teacherVo.getEducation());
        teacher.setCareer(teacherVo.getCareer());
        teacher.setIsfamous(teacherVo.getIsfamous());
        teacher.setAvatar(teacherVo.getAvatar());
        teacher.setStatus(teacherVo.getStatus());
        try{
            int i = teacherService.add(teacher);
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
            int i = teacherService.delete(id);
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

    @PostMapping("/upload")
    public ResultResponse<String> upload (@RequestParam("teacherImg") MultipartFile multipartFile) throws IOException {
        ResultResponse<String> result = null;
        try{

            String filename = multipartFile.getOriginalFilename();
            log.info(filename);
            filename = UUID.randomUUID().toString().replace("-","")+filename.substring(filename.lastIndexOf("."));
            log.info(filename);

            File file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }
            File f = new File(file,filename);
            multipartFile.transferTo(f);
            result = new ResultResponse<>(200, "上传成功",filename);
        }catch (RuntimeException e){
            e.printStackTrace();
            result = new ResultResponse<>(200, "上传失败");
        }
        return result;
    }
}
