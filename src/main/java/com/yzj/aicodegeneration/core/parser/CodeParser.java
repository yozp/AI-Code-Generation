package com.yzj.aicodegeneration.core.parser;

/**
 * 代码解析器接口
 * 采用策略模式
 */
public interface CodeParser<T> {

    /**
     * 解析代码内容
     * 
     * @param codeContent 原始代码内容
     * @return 解析后的结果对象
     */
    T parseCode(String codeContent);
}
