package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.UserResource;
import com.wton.oauth2.mapper.UserResourceMapper;
import com.wton.oauth2.service.IUserResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@Service
public class UserResourceServiceImpl extends ServiceImpl<UserResourceMapper, UserResource> implements IUserResourceService {

}
