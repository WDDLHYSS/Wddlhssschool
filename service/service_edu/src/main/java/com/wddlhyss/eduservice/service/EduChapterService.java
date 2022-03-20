package com.wddlhyss.eduservice.service;

import com.wddlhyss.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wddlhyss.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeChapterByCourseId(String courseId);
}
