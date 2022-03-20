package com.wddlhyss.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.commonutils.CourseWebVoOrder;
import com.wddlhyss.commonutils.JwtUtils;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.client.OrdersClient;
import com.wddlhyss.eduservice.entity.EduCourse;
import com.wddlhyss.eduservice.entity.VO.front.CourseFrontVo;
import com.wddlhyss.eduservice.entity.VO.front.CourseWebVo;
import com.wddlhyss.eduservice.entity.chapter.ChapterVo;
import com.wddlhyss.eduservice.service.EduChapterService;
import com.wddlhyss.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    @Autowired
    private OrdersClient ordersClient;

    @PostMapping("getFrontPostList/{page}/{limit}")
    public Result getTeacherFrontListt(@PathVariable long page, @PathVariable long limit,
                                       @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pagecCourse = new Page<>(page, limit);
        Map<String, Object> map = courseService.getCourseFrontList(pagecCourse, courseFrontVo);
        return Result.successResult().data(map);
    }

    @GetMapping("getCourseInfo/{courseid}")
    public Result getCourseInfo(@PathVariable String courseid, HttpServletRequest request) {
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseid);
        List<ChapterVo> chapterVoList = chapterService.getChapterByCourseId(courseid);
        boolean buyCourse = ordersClient.isBuyCourse(courseid, JwtUtils.getMemberIdByJwtToken(request));
        return Result.successResult().data("courseWebVo", courseWebVo)
                .data("chapterVoList", chapterVoList)
                .data("isBuy", buyCourse);
    }

    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo baseCourseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }


}
