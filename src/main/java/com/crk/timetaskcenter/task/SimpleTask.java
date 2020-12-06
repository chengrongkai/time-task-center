package com.crk.timetaskcenter.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author rongkai
 * @description:
 * @date 2020/12/6
 */
@Component("simpleTask")
public class SimpleTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public void run(String params){
        logger.debug("TestTask定时任务正在执行，参数为：{}", params);
    }
}
