package com.wddlhyss.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.entity.EduCourse;
import com.wddlhyss.eduservice.entity.EduTeacher;
import com.wddlhyss.eduservice.service.EduCourseService;
import com.wddlhyss.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eduservice/edu-indexfront")

public class IndexFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @GetMapping("index")
    public Result index() {

        List<EduCourse> courseList = courseService.selectlimitList();
        List<EduTeacher> teacherList = teacherService.selectlimitList();
        return Result.successResult().data("courseList",courseList).data("teacherList",teacherList);
    }
}
