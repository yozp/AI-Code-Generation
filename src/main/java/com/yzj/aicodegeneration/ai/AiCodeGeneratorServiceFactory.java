package com.yzj.aicodegeneration.ai;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.yzj.aicodegeneration.service.ChatHistoryService;
import dev.langchain4j.community.store.memory.chat.redis.RedisChatMemoryStore;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * AI 服务初始化工厂类
 */
@Configuration
@Slf4j
public class AiCodeGeneratorServiceFactory {

    @Resource
    private ChatModel chatModel;

    @Resource
    private StreamingChatModel streamingChatModel;

    @Resource
    private RedisChatMemoryStore redisChatMemoryStore;

    @Resource
    private ChatHistoryService chatHistoryService;

//    /**
//     * 方案 1 - 内置隔离机制
//     * 可以给 AI 服务方法增加 memoryId 注解和参数，然后通过 chatMemoryProvider 为每个 appId 分配对话记忆
//     * 所有应用共用同一个 AiService 实例
//     */
//    @Bean
//    public AiCodeGeneratorService aiCodeGeneratorService() {
////        return AiServices.create(AiCodeGeneratorService.class, chatModel);
//        return AiServices.builder(AiCodeGeneratorService.class)
//                .chatModel(chatModel)
//                .streamingChatModel(streamingChatModel)
//                // 根据 id 构建独立的对话记忆
//                .chatMemoryProvider(memoryId-> MessageWindowChatMemory
//                        .builder()
//                        .id(memoryId)
//                        .chatMemoryStore(redisChatMemoryStore)
//                        .maxMessages(20)
//                        .build()
//                )
//                .build();
//    }
//
//    /**
//     * 方案 2 - AI Service 隔离
//     * 给每个应用分配一个专属的 AI Service，每个 AI Service 绑定独立的对话记忆。
//     * （好处是不需要改动AiCodeGeneratorService的代码，或者说总的代码改动量更小）
//     * 这里默认提供一个 Bean
//     */
//    @Bean
//    public AiCodeGeneratorService aiCodeGeneratorService() {
//        return getAiCodeGeneratorService(0L);
//    }
//
//    /**
//     * 根据 appId 获取服务
//     * 可以被 aiCodeGeneratorService 方法自动创建
//     * 也可以被其他类调用动态创建（实现独立的AiCodeGeneratorService对象）
//     */
//    public AiCodeGeneratorService getAiCodeGeneratorService(long appId) {
//        // 根据 appId 构建独立的对话记忆
//        MessageWindowChatMemory chatMemory = MessageWindowChatMemory
//                .builder()
//                .id(appId)
//                .chatMemoryStore(redisChatMemoryStore)
//                .maxMessages(20)
//                .build();
//        return AiServices.builder(AiCodeGeneratorService.class)
//                .chatModel(chatModel)
//                .streamingChatModel(streamingChatModel)
//                .chatMemory(chatMemory)
//                .build();
//    }

    //----------------------------------------------------------------------------------------------------------------------

    //以下基于方案二继续进行改造

    /**
     * AI 服务实例缓存
     */
    private final Cache<Long, AiCodeGeneratorService> serviceCache = Caffeine.newBuilder()
            .maximumSize(1000) //最大缓存 1000 个实例
            .expireAfterWrite(Duration.ofMinutes(30)) //写入后 30 分钟过期
            .expireAfterAccess(Duration.ofMinutes(10)) //访问后 10 分钟过期
            .removalListener((key, value, cause) -> {
                log.debug("AI 服务实例被移除，appId: {}, 原因: {}", key, cause);
            })
            .build();

    /**
     * 1. 默认返回一个 Service
     * 主要作用是为了与之前的代码兼容
     */
    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService() {
        return getAiCodeGeneratorService(0L);
    }

    /**
     * 2. 根据 appId 获取服务（带缓存）
     * 可以被 aiCodeGeneratorService 方法自动创建
     * 也可以被其他类调用动态创建（实现独立的AiCodeGeneratorService对象）
     */
    public AiCodeGeneratorService getAiCodeGeneratorService(long appId) {
        return serviceCache.get(appId, this::createAiCodeGeneratorService);
    }

    /**
     * 3. 创建新的 AI 服务实例
     */
    private AiCodeGeneratorService createAiCodeGeneratorService(long appId) {
        log.info("为 appId: {} 创建新的 AI 服务实例", appId);
        // 根据 appId 构建独立的对话记忆
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory
                .builder()
                .id(appId)
                .chatMemoryStore(redisChatMemoryStore)
                .maxMessages(20)
                .build();
        // 从数据库加载历史对话到记忆中
        chatHistoryService.loadChatHistoryToMemory(appId, chatMemory, 20);
        return AiServices.builder(AiCodeGeneratorService.class)
                .chatModel(chatModel)
                .streamingChatModel(streamingChatModel)
                .chatMemory(chatMemory)
                .build();
    }

}
