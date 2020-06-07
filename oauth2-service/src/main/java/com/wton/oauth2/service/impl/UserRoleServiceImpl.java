package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.UserRole;
import com.wton.oauth2.mapper.UserRoleMapper;
import com.wton.oauth2.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
