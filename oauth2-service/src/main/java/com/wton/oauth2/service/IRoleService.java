package com.wton.oauth2.service;

import com.wton.oauth2.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
public interface IRoleService extends IService<Role> {

    Role addRole(Role role);

    Role updateRole(Role role);
}
