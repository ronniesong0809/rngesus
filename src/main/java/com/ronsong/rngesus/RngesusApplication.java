package com.ronsong.rngesus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ronsong.rngesus.mapper")
@SpringBootApplication
public class RngesusApplication {

    public static void main(String[] args) {
        SpringApplication.run(RngesusApplication.class, args);
    }

}
