package com.yzj.aicodegeneration.ai;

import com.yzj.aicodegeneration.ai.model.HtmlCodeResult;
import com.yzj.aicodegeneration.ai.model.MultiFileCodeResult;
import dev.langchain4j.service.SystemMessage;

/**
 * LangChain4j AI 服务接口
 */
public interface AiCodeGeneratorService {

    String generateCode(String userMessage);

    /**
     * 生成 HTML 代码
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-html-system-prompt.txt")
    HtmlCodeResult generateHtmlCode(String userMessage);

    /**
     * 生成多文件代码
     *
     * @param userMessage 用户消息
     * @return 生成的代码结果
     */
    @SystemMessage(fromResource = "prompt/codegen-multi-file-system-prompt.txt")
    MultiFileCodeResult generateMultiFileCode(String userMessage);


}
