package com.crk.timetaskcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.crk.timetaskcenter.dao")
public class TimeTaskCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeTaskCenterApplication.class, args);
    }

}
