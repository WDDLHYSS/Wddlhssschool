package com.wddlhyss.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
public interface EduTeacherService extends IService<EduTeacher> {

    List<EduTeacher> selectlimitList();

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageteacher);
}
