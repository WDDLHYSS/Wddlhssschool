package com.wddlhyss.oss.controller;

import com.wddlhyss.commonutils.Result;
import com.wddlhyss.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;


    //上传头像方法
    @PostMapping()
    public Result uploadFile(MultipartFile file) {
//获取上传文件
        //返回上传到oss的路径
        String url = ossService.uploadFileAvator(file);
        return Result.successResult().data("url",url);
    }

}
