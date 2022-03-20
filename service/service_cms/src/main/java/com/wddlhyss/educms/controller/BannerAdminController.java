package com.wddlhyss.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wddlhyss.commonutils.Result;
import com.wddlhyss.educms.entity.CrmBanner;
import com.wddlhyss.educms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.internal.operators.OperatorPublish;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-03-04
 */
@RestController
@RequestMapping("/educms/banneraddmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * 分页查询方法
     *
     * @param
     */
    @ApiOperation(value ="分页查询方法" )
    @GetMapping("pageBanner/{current}/{limit}")
    public Result pageBanner(@PathVariable long current,
                             @PathVariable long limit) {
        Page<CrmBanner> bannerPage = new Page<>();
        crmBannerService.page(bannerPage,null);

        return Result.successResult().data("items",bannerPage.getRecords()).data("total",bannerPage.getTotal());
    }
    /**
     *添加banner
     * @param
     */
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner banner){
        crmBannerService.save(banner);
        return Result.successResult();
    }
    /**
     *修改banner
     * @param
     */
    @PutMapping("updateBanner")
    public Result updateBanner(@RequestBody CrmBanner banner){
        crmBannerService.updateById(banner);
        return Result.successResult();
    }

    @GetMapping("getBanner/{id}")
    public Result getBanner(@PathVariable String id){
        CrmBanner banner = crmBannerService.getById(id);
        return Result.successResult().data("item",banner);
    }

    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id){
        crmBannerService.removeById(id);
        return Result.successResult();
    }
}

