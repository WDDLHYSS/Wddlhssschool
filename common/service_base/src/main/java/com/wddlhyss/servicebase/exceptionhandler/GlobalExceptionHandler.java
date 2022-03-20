package com.wddlhyss.servicebase.exceptionhandler;

import com.wddlhyss.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常执行什么方法
    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.errorResult().message("执行了全局异常处理");
    }

    //指定出现什么异常执行什么方法
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//为了返回数据
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.errorResult().message("执行了ArithmeticException异常处理");
    }

    //指定出现什么异常执行什么方法
    //自定义异常处理
    @ExceptionHandler(WddlhyssException.class)
    @ResponseBody
    public Result error(WddlhyssException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.errorResult().code(e.getCode()).message(e.getMsg());
    }

}
