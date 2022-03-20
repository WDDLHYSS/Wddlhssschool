package com.wddlhyss.eduservice.controller;


import com.wddlhyss.commonutils.Result;
import com.wddlhyss.eduservice.client.VodClient;
import com.wddlhyss.eduservice.entity.EduVideo;
import com.wddlhyss.eduservice.service.EduVideoService;
import com.wddlhyss.servicebase.exceptionhandler.WddlhyssException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-02-03
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return Result.successResult();
    }

    //删除小节
    @DeleteMapping("{id}")
    public Result deleteVideo(@PathVariable String id) {
        //通过小节id获取视频id
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节中是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id远程调用vod模块中删除视频的方法
            Result result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new WddlhyssException(20001, "删除视频失败，熔断器...");
            }
        }
        eduVideoService.removeById(id);
        return Result.successResult();
    }

    //修改小节 TODO(删除视频时进行完善)


}

