package com.wddlhyss.educms.controller;

import com.wddlhyss.commonutils.Result;
import com.wddlhyss.educms.entity.CrmBanner;
import com.wddlhyss.educms.service.CrmBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/educms/bannerfront")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;


    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> crmBanners = crmBannerService.selectAllBanner();
        return Result.successResult().data("list",crmBanners);
    }
}
