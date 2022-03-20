package com.wddlhyss.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.servicebase.exceptionhandler.WddlhyssException;
import com.wddlhyss.vod.service.VodService;
import com.wddlhyss.vod.utils.ConstantVodUtils;
import com.wddlhyss.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;


    /**
     * 上传视频到阿里云
     */
    @PostMapping("uploadAliyunVideo")
    public Result uploadAliyunVideo(MultipartFile file) {
        String videoId = vodService.uoloadVideo(file);
        return Result.successResult().data("videoId", videoId);
    }

    @DeleteMapping("removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //xiangrequest中设置视频id
            request.setVideoIds(id);
            //调用初始化对象方法进行删除
            client.getAcsResponse(request);
            return Result.successResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WddlhyssException(20001, "删除视频失败");
        }

    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @DeleteMapping("delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreAlyVideo(videoIdList);
        return Result.successResult();
    }

    //获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public Result getPlayAuth(@PathVariable String id) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            request.setVideoId(id);
            GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
            String playAuth = acsResponse.getPlayAuth();
            return Result.successResult().data("playAuth", playAuth);
        } catch (Exception e) {
            throw new WddlhyssException(20001, "获取凭证失败");
        }
    }
}
