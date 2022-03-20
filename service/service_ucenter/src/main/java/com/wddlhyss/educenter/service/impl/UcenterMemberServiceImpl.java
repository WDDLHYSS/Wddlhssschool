package com.wddlhyss.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wddlhyss.commonutils.JwtUtils;
import com.wddlhyss.commonutils.MD5;
import com.wddlhyss.educenter.entity.UcenterMember;
import com.wddlhyss.educenter.entity.vo.RegisterVo;
import com.wddlhyss.educenter.mapper.UcenterMemberMapper;
import com.wddlhyss.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wddlhyss.servicebase.RedisConfig;
import com.wddlhyss.servicebase.exceptionhandler.WddlhyssException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-03-06
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(UcenterMember member) {
        String phone = member.getMobile();
        String password = member.getPassword();

        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new WddlhyssException(20001, "登陆失败");
        }
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("mobile", phone);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if (ucenterMember == null) {
            throw new WddlhyssException(20001, "登陆失败");
        }
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())) {
            throw new WddlhyssException(20001, "登陆失败");
        }
        if (ucenterMember.getIsDisabled()) {
            throw new WddlhyssException(20001, "登陆失败");
        }
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(nickname)) {
            throw new WddlhyssException(20001, "注册失败");
        }
        String vercode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(vercode)) {
            throw new WddlhyssException(20001, "注册失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new WddlhyssException(20001, "注册失败");
        }
        UcenterMember member  = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://schoolofcloud.oss-cn-beijing.aliyuncs.com/2022/02/10/8d3d92ed50c8464e81ac2d225384c0bafile.png");
        baseMapper.insert(member);

    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper wrapper =new QueryWrapper();
        wrapper.eq("openid",openid);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        return ucenterMember;
    }

    //查询某一天注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
