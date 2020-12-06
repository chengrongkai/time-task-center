package com.crk.timetaskcenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crk.timetaskcenter.common.aop.annotation.LogAnnotation;
import com.crk.timetaskcenter.common.utils.DataResult;
import com.crk.timetaskcenter.entity.TimeTaskLogEntity;
import com.crk.timetaskcenter.service.TimeTaskLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 定时任务日志
 *
 * @author rongkai
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "定时任务日志")
@RestController
@RequestMapping("/sysJobLog")
public class TimeTaskLogController {
    @Resource
    private TimeTaskLogService timeTaskLogService;

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("sysJob:list")
    public DataResult findListByPage(@RequestBody TimeTaskLogEntity sysJobLog){
        Page page = new Page(sysJobLog.getPage(), sysJobLog.getLimit());
        LambdaQueryWrapper<TimeTaskLogEntity> queryWrapper = Wrappers.lambdaQuery();
        //查询条件示例
        if (!StringUtils.isEmpty(sysJobLog.getJobId())) {
            queryWrapper.like(TimeTaskLogEntity::getJobId, sysJobLog.getJobId());
        }
        queryWrapper.orderByDesc(TimeTaskLogEntity::getCreateTime);
        IPage<TimeTaskLogEntity> iPage = timeTaskLogService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }

    @ApiOperation(value = "清空定时任务日志")
    @DeleteMapping("/delete")
    @RequiresPermissions("sysJob:delete")
    @LogAnnotation(title = "清空")
    public DataResult delete() {
        timeTaskLogService.remove(Wrappers.emptyWrapper());
        return DataResult.success();
    }

}
