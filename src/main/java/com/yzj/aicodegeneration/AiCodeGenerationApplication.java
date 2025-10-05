package com.yzj.aicodegeneration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.yzj.aicodegeneration.mapper")
public class AiCodeGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeGenerationApplication.class, args);
    }

}
