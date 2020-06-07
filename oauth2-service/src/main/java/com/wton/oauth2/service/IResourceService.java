package com.wton.oauth2.service;

import com.wton.oauth2.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
public interface IResourceService extends IService<Resource> {

    Resource addResource(Resource resource);

    Resource updateResource(Resource resource);
}
