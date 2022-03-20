package com.wddlhyss.eduorder.client;


import com.wddlhyss.commonutils.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("service-ucenter")
public interface UcneterClient {

    @PostMapping("/educenter/member/getuserInfoOrder/{id}")
    public UcenterMemberOrder getuserInfoOrder(@PathVariable("id") String id);
}
