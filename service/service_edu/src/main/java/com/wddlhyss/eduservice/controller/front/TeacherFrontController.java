package com.wddlhyss.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.entity.EduCourse;
import com.wddlhyss.eduservice.entity.EduTeacher;
import com.wddlhyss.eduservice.entity.subject.OneSubject;
import com.wddlhyss.eduservice.service.EduCourseService;
import com.wddlhyss.eduservice.service.EduTeacherService;
import org.aspectj.apache.bcel.generic.RET;
import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.web.bind.annotation.*;
import rx.internal.schedulers.NewThreadWorker;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/edu-teacher")
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    @PostMapping("getTeacherFrontListt/{page}/{limit}")
    public Result getTeacherFrontListt(@PathVariable long page,@PathVariable long limit){

        Page<EduTeacher> pageteacher = new Page<>(page,limit);
        Map<String, Object>  map = teacherService.getTeacherFrontList(pageteacher);
        return Result.successResult().data(map);
    }

    @GetMapping("getTeacherInfoById/{teacherid}")
    public Result getTeacherInfoById(@PathVariable String teacherid){

        EduTeacher eduTeacher= teacherService.getById(teacherid);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherid);
        List<EduCourse> eduCourses = courseService.list(wrapper);
        return Result.successResult().data("teacher",eduTeacher).data("courseList",eduCourses);
    }
}
