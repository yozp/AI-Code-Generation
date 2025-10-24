package com.yzj.aicodegeneration;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude =
        {RedisEmbeddingStoreAutoConfiguration.class})//排除 embedding 的自动装配，因为本项目用不到
@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.yzj.aicodegeneration.mapper")
public class AiCodeGenerationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiCodeGenerationApplication.class, args);
    }

}
