package com.wddlhyss.educenter.service;

import com.wddlhyss.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wddlhyss.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-03-06
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getByOpenid(String openid);

    Integer countRegisterDay(String day);
}
