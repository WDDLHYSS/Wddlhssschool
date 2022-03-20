package com.wddlhyss.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wddlhyss.eduservice.entity.VO.CourseInfoVo;
import com.wddlhyss.eduservice.entity.VO.CoursePublishVo;
import com.wddlhyss.eduservice.entity.VO.front.CourseFrontVo;
import com.wddlhyss.eduservice.entity.VO.front.CourseWebVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseVo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    List<EduCourse> selectlimitList();

    Map<String, Object> getCourseFrontList(Page<EduCourse> pagecCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseid);
}
