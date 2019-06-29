package com.note.apple;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication
@MapperScan("com.note.apple.dao")
public class AppleApplication {

    public static void main(String[] args) {
        System.out.println("****************启动我的笔记本***************");
        SpringApplication.run(AppleApplication.class, args);
    }

}
