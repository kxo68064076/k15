package com.woniu.k15;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.woniu.k15.mapper")
@EnableSwagger2
@EnableTransactionManagement
public class K15Application {

    public static void main(String[] args) {
        SpringApplication.run(K15Application.class, args);
    }

}
