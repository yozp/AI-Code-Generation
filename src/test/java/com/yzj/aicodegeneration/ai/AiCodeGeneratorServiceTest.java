package com.yzj.aicodegeneration.ai;

import com.yzj.aicodegeneration.ai.model.HtmlCodeResult;
import com.yzj.aicodegeneration.ai.model.MultiFileCodeResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeGeneratorServiceTest {

    @Resource
    private AiCodeGeneratorService aiCodeGeneratorService;

    @Test
    void generateHtmlCode() {
        //String result = aiCodeGeneratorService.generateHtmlCode("做个程序员yzj的工作记录小工具");
        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode("做个程序员yzj的工作记录小工具");
        Assertions.assertNotNull(result);
    }

    @Test
    void generateMultiFileCode() {
        //String multiFileCode = aiCodeGeneratorService.generateMultiFileCode("做个程序员yzj的留言板");
        MultiFileCodeResult multiFileCode = aiCodeGeneratorService.generateMultiFileCode("做个程序员yzj的留言板");
        Assertions.assertNotNull(multiFileCode);
    }

    //测试方案一的对话记忆隔离机制
//    @Test
//    void testChatMemory() {
//        HtmlCodeResult result = aiCodeGeneratorService.generateHtmlCode(1, "做个程序员鱼皮的工具网站，总代码量不超过 20 行");
//        Assertions.assertNotNull(result);
//        result = aiCodeGeneratorService.generateHtmlCode(1, "不要生成网站，告诉我你刚刚做了什么？");
//        Assertions.assertNotNull(result);
//        result = aiCodeGeneratorService.generateHtmlCode(2, "做个程序员鱼皮的工具网站，总代码量不超过 20 行");
//        Assertions.assertNotNull(result);
//        result = aiCodeGeneratorService.generateHtmlCode(2, "不要生成网站，告诉我你刚刚做了什么？");
//        Assertions.assertNotNull(result);
//    }

}
