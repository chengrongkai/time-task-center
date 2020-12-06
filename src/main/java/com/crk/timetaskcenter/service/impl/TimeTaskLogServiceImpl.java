package com.crk.timetaskcenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crk.timetaskcenter.dao.TimeTaskLogMapper;
import com.crk.timetaskcenter.entity.TimeTaskLogEntity;
import com.crk.timetaskcenter.service.TimeTaskLogService;
import org.springframework.stereotype.Service;

/**
 * 定时任务 服务类
 *
 * @author rongkai
 * @version V1.0
 * @date 2020年3月18日
 */
@Service("TimeTaskLogService")
public class TimeTaskLogServiceImpl extends ServiceImpl<TimeTaskLogMapper, TimeTaskLogEntity> implements TimeTaskLogService {


}