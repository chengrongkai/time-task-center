package com.crk.timetaskcenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crk.timetaskcenter.dao.SysLogMapper;
import com.crk.timetaskcenter.entity.SysLog;
import com.crk.timetaskcenter.service.LogService;
import org.springframework.stereotype.Service;

/**
 * 系统日志
 *
 * @author rongkai
 * @version V1.0
 * @date 2020年3月18日
 */
@Service
public class LogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements LogService {
}
