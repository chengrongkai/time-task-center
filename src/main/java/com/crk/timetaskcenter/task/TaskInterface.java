package com.crk.timetaskcenter.task;

/**
 * @author rongkai
 * @description: 定时任务通用接口，凡自定义的定时任务皆需实现该接口
 * @date 2020/12/6
 */
public interface TaskInterface<T> {
    /**
     * 定时任务执行方法
     * @param params
     */
    public void run(T params);
}
