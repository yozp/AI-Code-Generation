package com.yzj.aicodegeneration.model.dto.app;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新应用请求类
 */
@Data
public class AppUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 应用名称
     */
    private String appName;

    private static final long serialVersionUID = 1L;
}
