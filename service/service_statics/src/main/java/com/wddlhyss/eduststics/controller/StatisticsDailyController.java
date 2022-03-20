package com.wddlhyss.eduststics.controller;


import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduststics.client.UcenterClient;
import com.wddlhyss.eduststics.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService service;

    @PostMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day){
        service.registerCount(day);
        return Result.successResult();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = service.getShowData(type,begin,end);
        return Result.successResult().data(map);
    }
}

