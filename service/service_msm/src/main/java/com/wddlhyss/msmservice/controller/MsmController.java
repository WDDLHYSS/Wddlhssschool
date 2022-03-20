package com.wddlhyss.msmservice.controller;

import com.aliyuncs.utils.StringUtils;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.msmservice.service.MsmService;
import com.wddlhyss.msmservice.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin
@RequestMapping("/edumsm/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信的方法
    @ApiOperation(value = "发送验证码")
    @GetMapping("send/{phone}")
    public Result sendMsm(@ApiParam("用户手机号") @PathVariable String phone) {

        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.successResult();
        }

        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        boolean isSend = msmService.send(param, phone);
        if (isSend) {
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.successResult();
        } else {
            return Result.errorResult().message("短信发送失败");
        }

    }
}
