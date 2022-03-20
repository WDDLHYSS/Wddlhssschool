package com.wddlhyss.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uoloadVideo(MultipartFile file);

    void removeMoreAlyVideo(List videoIdList);
}
