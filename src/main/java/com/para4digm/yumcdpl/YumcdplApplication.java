package com.para4digm.yumcdpl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@MapperScan("com.para4digm.yumcdpl.mapper")
public class YumcdplApplication {

    public static void main(String[] args) {
        SpringApplication.run(YumcdplApplication.class, args);
    }
}