package com.crk.timetaskcenter.controller;

import com.crk.timetaskcenter.common.utils.DataResult;
import com.crk.timetaskcenter.service.HomeService;
import com.crk.timetaskcenter.service.HttpSessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 首页
 *
 * @author rongkai
 * @version V1.0
 * @date 2020年3月18日
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "首页数据")
public class HomeController {
    @Resource
    private HomeService homeService;
    @Resource
    private HttpSessionService httpSessionService;

    @GetMapping("/home")
    @ApiOperation(value = "获取首页数据接口")
    public DataResult getHomeInfo() {
        //通过access_token拿userId
        String userId = httpSessionService.getCurrentUserId();
        DataResult result = DataResult.success();
        result.setData(homeService.getHomeInfo(userId));
        return result;
    }
}
