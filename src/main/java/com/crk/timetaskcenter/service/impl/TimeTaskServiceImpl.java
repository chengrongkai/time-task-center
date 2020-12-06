package com.crk.timetaskcenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crk.timetaskcenter.common.exception.BusinessException;
import com.crk.timetaskcenter.common.job.ScheduleUtils;
import com.crk.timetaskcenter.common.utils.Constant;
import com.crk.timetaskcenter.dao.TimeTaskMapper;
import com.crk.timetaskcenter.entity.TimeTaskEntity;
import com.crk.timetaskcenter.service.TimeTaskService;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务 服务类
 *
 * @author rongkai
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class TimeTaskServiceImpl extends ServiceImpl<TimeTaskMapper, TimeTaskEntity> implements TimeTaskService {
    @Resource
    private Scheduler scheduler;
    @Resource
    private TimeTaskMapper timeTaskMapper;
    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init(){
        List<TimeTaskEntity> scheduleJobList = this.list();
        for(TimeTaskEntity scheduleJob : scheduleJobList){
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public void saveJob(TimeTaskEntity timeTask) {
        timeTask.setStatus(Constant.SCHEDULER_STATUS_NORMAL);
        this.save(timeTask);

        ScheduleUtils.createScheduleJob(scheduler, timeTask);
    }

    @Override
    public void updateJobById(TimeTaskEntity timeTask) {
        TimeTaskEntity TimeTaskEntity = this.getById(timeTask.getId());
        if (TimeTaskEntity == null) {
            throw new BusinessException("获取定时任务异常");
        }
        timeTask.setStatus(TimeTaskEntity.getStatus());
        ScheduleUtils.updateScheduleJob(scheduler, timeTask);

        this.updateById(timeTask);
    }

    @Override
    public void delete(List<String> ids) {
        for(String jobId : ids){
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        timeTaskMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(List<String> ids) {
        for(String jobId : ids){
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(List<String> ids) {
        for(String jobId : ids){
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(ids, Constant.SCHEDULER_STATUS_PAUSE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(List<String> ids) {
        for(String jobId : ids){
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(ids, Constant.SCHEDULER_STATUS_NORMAL);
    }

    @Override
    public void updateBatch(List<String> ids, int status){
        ids.parallelStream().forEach(id -> {
            TimeTaskEntity TimeTaskEntity = new TimeTaskEntity();
            TimeTaskEntity.setId(id);
            TimeTaskEntity.setStatus(status);
            baseMapper.updateById(TimeTaskEntity);
        });
    }
}