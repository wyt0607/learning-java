package com.wton.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wton.oauth2.entity.Role;
import com.wton.oauth2.mapper.RoleMapper;
import com.wton.oauth2.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public Role addRole(Role role) {
        QueryWrapper<Role> queryWrapper = Wrappers.query();
        queryWrapper.eq("rolename", role.getRolename());
        if (Objects.isNull(getOne(queryWrapper))) {
            if (save(role)) {
                return role;
            }
        }
        return null;
    }

    @Override
    public Role updateRole(Role role) {
        Role updateRole = getById(role.getId());
        if (Objects.nonNull(updateRole)) {
            BeanUtils.copyProperties(role, updateRole);
            if (updateById(updateRole)) {
                return updateRole;
            }
        }
        return null;
    }
}
