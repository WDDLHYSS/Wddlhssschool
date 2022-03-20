package com.wddlhyss.eduservice.controller;


import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.entity.subject.OneSubject;
import com.wddlhyss.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-10
 */

@RestController
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    @PostMapping("addSubject")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return Result.successResult();
    }

    //课程分类列表（树形）
    @GetMapping("getAllSubject")
    public Result getAllSubject() {
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return Result.successResult().data("list",list);
    }

}

