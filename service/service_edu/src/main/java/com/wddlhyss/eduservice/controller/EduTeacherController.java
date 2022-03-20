package com.wddlhyss.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.entity.EduTeacher;
import com.wddlhyss.eduservice.entity.VO.TeacherQuerry;
import com.wddlhyss.eduservice.service.EduTeacherService;
import com.wddlhyss.servicebase.exceptionhandler.WddlhyssException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //查询讲师所有数据  rest风格
    @ApiOperation(value = "查询讲师所有数据")
    @GetMapping("findAll")
    public Result findAll() {

        //调用service实现查询
        List<EduTeacher> teacherList = teacherService.list(null);
        return Result.successResult().data("items", teacherList);
    }

    //逻辑删除讲师
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(@PathVariable String id) {
        boolean removeByIdFlag = teacherService.removeById(id);
        if (removeByIdFlag) {
            return Result.successResult();
        } else {
            return Result.errorResult();
        }
        //上述if else可写为三元组 return removeByIdFlag ? Result.successResult() : Result.errorResult();
    }

    //分页查询讲师
    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@PathVariable long current,
                                  @PathVariable long limit) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页
        //调用方法时底层封装，将分页所有数据封装到pageTeacher中
        teacherService.page(pageTeacher, null);

        long total = pageTeacher.getTotal();//总数居数
        List<EduTeacher> records = pageTeacher.getRecords();//每页数据list集合
     /* Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return Result.successResult().data(map);*/
        return Result.successResult().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "条件分页查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current,
                                       @PathVariable long limit,
                                       @RequestBody(required = false) TeacherQuerry teacherQuerry) {
        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        //多条件组合查询 mybatis 动态sql
        //判断条件值是否为空，若不为空拼接条件
        String name = teacherQuerry.getName();
        Integer level = teacherQuerry.getLevel();
        String begin = teacherQuerry.getBegin();
        String end = teacherQuerry.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        //排序
        queryWrapper.orderByDesc("gmt_create");
        teacherService.page(pageTeacher, queryWrapper);

        long total = pageTeacher.getTotal();//总数居数
        List<EduTeacher> records = pageTeacher.getRecords();//每页数据list集合

        return Result.successResult().data("total", total).data("rows", records);
    }

    //添加讲师接口
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean saveFlag = teacherService.save(eduTeacher);
        if (saveFlag) {
            return Result.successResult();
        } else {
            return Result.errorResult();
        }
    }

    //根据讲师id进行查询
    @ApiOperation(value = "根据讲师id进行查询信息")
    @GetMapping("getTeacher/{id}")
    public Result getTeacherById(@PathVariable String id) {
        EduTeacher teacherById = teacherService.getById(id);
        return Result.successResult().data("teacher", teacherById);
    }

    //讲师修改功能
    @ApiOperation(value = "讲师修改信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean updateflag = teacherService.updateById(eduTeacher);
        if (updateflag) {
            return Result.successResult();
        } else {
            return Result.errorResult();
        }
    }

}

