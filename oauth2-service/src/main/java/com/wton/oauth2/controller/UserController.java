package com.wton.oauth2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wton.oauth2.dto.PageDTO;
import com.wton.oauth2.dto.UserDTO;
import com.wton.oauth2.entity.User;
import com.wton.oauth2.entity.UserResource;
import com.wton.oauth2.entity.UserRole;
import com.wton.oauth2.extend.RestStatus;
import com.wton.oauth2.extend.RestStatusUtil;
import com.wton.oauth2.service.IUserResourceService;
import com.wton.oauth2.service.IUserRoleService;
import com.wton.oauth2.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/oauth2/user")
public class UserController extends AbstractBaseController {

    private IUserService userService;
    private IUserRoleService userRoleService;
    private IUserResourceService userResourceService;

    public UserController(IUserService userService, IUserRoleService userRoleService, IUserResourceService userResourceService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.userResourceService = userResourceService;
    }

    @GetMapping
    public RestStatus<PageDTO<User>> getUser(UserDTO userDTO) {
        User user = extractParams(userDTO, User.class);
        QueryWrapper<User> queryWrapper = Wrappers.query(user);
        Page<User> userPage = userService.page(getPage(userDTO), queryWrapper);
        return RestStatusUtil.success(getPageDTO(userPage), RestStatusUtil.SUCCESS_DES);
    }


    @PostMapping
    public RestStatus<User> addUser(UserDTO userDTO) {
        User user = extractParams(userDTO, User.class);
        user = userService.addUser(user);
        return RestStatusUtil.success(user, RestStatusUtil.SUCCESS_DES);
    }

    @PutMapping
    public RestStatus<User> updateUser(UserDTO userDTO) {
        User user = extractParams(userDTO, User.class);
        user = userService.updateUser(user);
        return RestStatusUtil.success(user, RestStatusUtil.SUCCESS_DES);
    }

    @DeleteMapping
    public RestStatus<String> delUser(String userId) {
        userService.removeById(userId);
        return RestStatusUtil.success();
    }


    @PostMapping("/role")
    public RestStatus<String> addUserRole(String userId, String... roleId) {
        List<UserRole> userRoleList = Stream.of(roleId).map(temp -> new UserRole(userId, temp)).collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
        return RestStatusUtil.success();
    }

    @DeleteMapping("/role")
    public RestStatus<String> delUserRole(String userId, String... roleId) {
        QueryWrapper<UserRole> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId);
        if (Objects.nonNull(roleId)) {
            queryWrapper.in("role_id", roleId);
        }
        userRoleService.remove(queryWrapper);
        return RestStatusUtil.success();
    }


    @PostMapping("/resource")
    public RestStatus<String> addUserResource(String userId, String... resourceId) {
        List<UserResource> userRoleList = Stream.of(resourceId).map(temp -> new UserResource(userId, temp)).collect(Collectors.toList());
        userResourceService.saveBatch(userRoleList);
        return RestStatusUtil.success();
    }

    @DeleteMapping("/resource")
    public RestStatus<String> delUserResource(String userId, String... resourceId) {
        QueryWrapper<UserResource> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId);
        if (Objects.nonNull(resourceId)) {
            queryWrapper.in("resource_id", resourceId);
        }
        userResourceService.remove(queryWrapper);
        return RestStatusUtil.success();
    }


}
