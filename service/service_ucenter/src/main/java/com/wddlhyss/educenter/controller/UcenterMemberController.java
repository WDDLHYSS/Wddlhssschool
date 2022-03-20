package com.wddlhyss.educenter.controller;


import com.wddlhyss.commonutils.JwtUtils;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.commonutils.UcenterMemberOrder;
import com.wddlhyss.educenter.entity.UcenterMember;
import com.wddlhyss.educenter.entity.vo.RegisterVo;
import com.wddlhyss.educenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-06
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public Result login(@RequestBody UcenterMember member) {
        String token = ucenterMemberService.login(member);
        return Result.successResult().data("token", token);
    }

    //TODO 注册时无论成功与否前端都会返回true
    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public Result register(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return Result.successResult();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public Result getLoginInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        return Result.successResult().data("userInfo", member);

    }

    @PostMapping("getuserInfoOrder/{id}")
    public UcenterMemberOrder getuserInfoOrder(@PathVariable String id) {
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberOrder ucenterMemberCOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(ucenterMember, ucenterMemberCOrder);
        return ucenterMemberCOrder;
    }

    //查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public Result countRegister(@PathVariable String day) {
        Integer count = ucenterMemberService.countRegisterDay(day);
        return Result.successResult().data("countRegister",count);
    }

}

