package com.wddlhyss.oss.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.wddlhyss.oss.service.OssService;
import com.wddlhyss.oss.util.ConstantYmlUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadFileAvator(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantYmlUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantYmlUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantYmlUtils.ACCESS_KEY_SECRET;
        String buckerName = ConstantYmlUtils.BUCKET_NAME;

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();

            //获取文件名称
            String fileName = file.getOriginalFilename();

            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // yuy76t5rew01.jpg
            fileName = uuid + fileName;

            //2 把文件按照日期进行分类
            //获取当前日期
            //   2019/11/12
            String datePath = new DateTime().toString("yyyy/MM/dd");
            //拼接
            //  2019/11/12/ewtqr313401.jpg
            fileName = datePath + "/" + fileName;

            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            //第一个参数backet名称，第二个参数上传到oss文件路径与名称,第三个参数上传输入流
            ossClient.putObject(buckerName, fileName, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //将上传oss之后的路径返回
            //https://wddlhyssschool.oss-cn-beijing.aliyuncs.com/123.jpg
            String url = "https://" + buckerName + "." + endpoint + "/" + fileName;
            System.out.println(url);
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
