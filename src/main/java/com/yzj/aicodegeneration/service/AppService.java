package com.yzj.aicodegeneration.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.yzj.aicodegeneration.model.dto.app.AppQueryRequest;
import com.yzj.aicodegeneration.model.entity.App;
import com.yzj.aicodegeneration.model.vo.AppVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author <a href="https://github.com/yozp">yunikon</a>
 */
public interface AppService extends IService<App> {

    /**
     * 获取应用封装类
     *
     * @param app
     * @return
     */
    AppVO getAppVO(App app);

    /**
     * 构造应用查询条件
     *
     * @param appQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 获取应用封装类列表
     *
     * @param appList
     * @return
     */
    List<AppVO> getAppVOList(List<App> appList);
}
