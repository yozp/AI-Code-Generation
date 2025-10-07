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
}
