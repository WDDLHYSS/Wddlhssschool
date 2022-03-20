package com.wddlhyss.eduservice.client;

import com.wddlhyss.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradFeifnClient implements VodClient{

    @Override
    public Result removeAlyVideo(String id) {
        return Result.errorResult().message("删除视频出错了");
    }


    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return Result.errorResult().message("删除多个视频出错了");
    }
}
