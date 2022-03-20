package com.wddlhyss.eduservice.controller;

import com.wddlhyss.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    //login
    @PostMapping("login")
    public Result login(){
        return Result.successResult().data("token","admin");
    }

    //info
    @GetMapping("info")
    public Result info(){
        return Result.successResult().data("roles","[admin]").data("name","admin").data("avatar","https://pic1.zhimg.com/v2-d58ce10bf4e01f5086c604a9cfed29f3_r.jpg?source=1940ef5c");
    }
}
