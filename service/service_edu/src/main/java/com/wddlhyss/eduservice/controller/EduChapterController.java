package com.wddlhyss.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.javaws.exceptions.CacheAccessException;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.entity.EduChapter;
import com.wddlhyss.eduservice.entity.EduVideo;
import com.wddlhyss.eduservice.entity.chapter.ChapterVo;
import com.wddlhyss.eduservice.service.EduChapterService;
import com.wddlhyss.eduservice.service.EduVideoService;
import com.wddlhyss.servicebase.exceptionhandler.WddlhyssException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //课程大纲列表
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterByCourseId(courseId);
        return Result.successResult().data("allChapterVideo", list);
    }

    //添加章节
    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return Result.successResult();
    }

    //根据id查询
    @GetMapping("getChaptetInfo/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return Result.successResult().data("chapter", eduChapter);
    }

    //修改章节
    @PostMapping("updateChaptetInfo")
    public Result updateChaptetInfo(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return Result.successResult();
    }

    //删除章节
    @DeleteMapping("{chapterId}")
    public Result deleteChaptetInfo(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return Result.successResult();
        } else {
            return Result.errorResult();
        }
    }
}

