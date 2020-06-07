package com.wton.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wton.oauth2.entity.*;
import com.wton.oauth2.mapper.UserMapper;
import com.wton.oauth2.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private IUserRoleService userRoleService;
    private IUserResourceService userResourceService;
    private IRoleService roleService;
    private IRoleResourceService roleResourceService;
    private IResourceService resourceService;

    public UserServiceImpl(IUserRoleService userRoleService, IUserResourceService userResourceService, IRoleService roleService, IRoleResourceService roleResourceService, IResourceService resourceService) {
        this.userRoleService = userRoleService;
        this.userResourceService = userResourceService;
        this.roleService = roleService;
        this.roleResourceService = roleResourceService;
        this.resourceService = resourceService;
    }

    @Override
    public List<GrantedAuthority> getUserAuthority(String username, String password) {

        QueryWrapper<User> userWrapper = Wrappers.query();
        userWrapper.eq("username", username);
        userWrapper.eq("password", password);
        User user = getOne(userWrapper);
        List<String> authorityList = new ArrayList<>();
        authorityList.add("admin");
        if (Objects.nonNull(user)) {
            String userId = user.getId();

            QueryWrapper<UserRole> userRoleWrapper = Wrappers.query();
            userRoleWrapper.eq("user_id", userId);
            List<UserRole> userRoleList = userRoleService.list(userRoleWrapper);

            List<String> resourceIdList = new ArrayList<>();

            for (UserRole userRole : userRoleList) {
                QueryWrapper<Role> roleWrapper = Wrappers.query();
                roleWrapper.eq("role_id", userRole.getRoleId());
                List<Role> roleList = roleService.list(roleWrapper);

                authorityList.addAll(roleList.stream()
                        .map(Role::getAuthority)
                        .collect(Collectors.toList()));

                QueryWrapper<RoleResource> roleResourceWrapper = Wrappers.query();
                roleResourceWrapper.eq("role_id", userRole.getRoleId());
                List<RoleResource> roleResourceList = roleResourceService.list(roleResourceWrapper);

                resourceIdList.addAll(roleResourceList.stream()
                        .map(RoleResource::getResourceId)
                        .collect(Collectors.toList()));

                QueryWrapper<UserResource> userResourceWrapper = Wrappers.query();
                userRoleWrapper.eq("user_id", userId);
                List<UserResource> userResourceList = userResourceService.list(userResourceWrapper);

                resourceIdList.addAll(userResourceList.stream()
                        .map(UserResource::getResourceId)
                        .collect(Collectors.toList()));
            }
            if (!resourceIdList.isEmpty()) {
                authorityList.addAll(resourceService.listByIds(resourceIdList).stream()
                        .map(Resource::getAuthority)
                        .collect(Collectors.toList()));
            }
        }


        return authorityList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public User addUser(User user) {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("username", user.getUsername());
        if (Objects.isNull(getOne(queryWrapper))) {
            if (save(user)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User updateUser(User user) {
        User updateUser = getById(user.getId());
        if (Objects.nonNull(updateUser)) {
            BeanUtils.copyProperties(user, updateUser);
            if (updateById(updateUser)) {
                return updateUser;
            }
        }
        return null;
    }
}
