package com.wddlhyss.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.entity.EduCourse;
import com.wddlhyss.eduservice.entity.EduTeacher;
import com.wddlhyss.eduservice.entity.VO.CourseInfoVo;
import com.wddlhyss.eduservice.entity.VO.CoursePublishVo;
import com.wddlhyss.eduservice.entity.VO.CourseQuerry;
import com.wddlhyss.eduservice.entity.VO.TeacherQuerry;
import com.wddlhyss.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.internal.constraintvalidators.hv.Mod10CheckValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.RealmException;

import java.nio.Buffer;
import java.sql.PseudoColumnUsage;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;


    //课程列表基本实现
    //TODO 完善条件查询带分页
    @ApiOperation(value = "条件分页查询课程")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(@PathVariable long current,
                                       @PathVariable long limit,
                                       @RequestBody(required = false) CourseQuerry courseQuerry) {
        //创建page对象
        Page<EduCourse> pageCourse = new Page<>(current, limit);

        //构建条件
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        //多条件组合查询 mybatis 动态sql
        //判断条件值是否为空，若不为空拼接条件
        String title = courseQuerry.getTitle();
        String status = courseQuerry.getStatus();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(status)) {
            queryWrapper.eq("status", status);
        }
        eduCourseService.page(pageCourse, queryWrapper);

        long total = pageCourse.getTotal();//总数居数
        List<EduCourse> records = pageCourse.getRecords();//每页数据list集合

        return Result.successResult().data("total", total).data("rows", records);
    }

    //查询所有课程
    @GetMapping
    public Result getCourseList(){
        List<EduCourse> eduCourseList = eduCourseService.list(null);
        return Result.successResult().data("list",eduCourseList);
    }


    //添加课程信息
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
       String id = eduCourseService.saveCourseVo(courseInfoVo);
        return Result.successResult().data("courseId",id);
    }

    //根据课程查询课程基本信息
    @GetMapping("getcourseInfo/{courseId}")
    public Result getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo  = eduCourseService.getCourseInfo(courseId);
        return Result.successResult().data("courseInfoVo",courseInfoVo);
    }

    //修改课程信息
    @PostMapping("updatecourseInfo")
    public Result updatecourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return Result.successResult();
    }
    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.publishCourseInfo(id);
        return Result.successResult().data("publishCourse", coursePublishVo);
    }

    //课程最终发布
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(EduCourse.Course_NORMAL);
        eduCourseService.updateById(eduCourse);
        return Result.successResult();
    }

    @DeleteMapping("{courseId}")
    public Result deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return Result.successResult();
    }
}

