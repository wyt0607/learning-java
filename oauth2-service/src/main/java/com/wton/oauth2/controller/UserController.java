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
import com.wton.oauth2.service.IUserResourceService;
import com.wton.oauth2.service.IUserRoleService;
import com.wton.oauth2.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.hateoas.Link;
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
@Api(tags = {"用户管理"})
@RequestMapping("/oauth2/user")
public class UserController extends AbstractBaseController {

    private final UserController virtualInstance;
    private final IUserService userService;
    private final IUserRoleService userRoleService;
    private final IUserResourceService userResourceService;


    public UserController(IUserService userService, IUserRoleService userRoleService, IUserResourceService userResourceService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.userResourceService = userResourceService;
        this.virtualInstance = virtualInstance(UserController.class);
    }

    @GetMapping
    public RestStatus<PageDTO<User>> getUser(UserDTO userDTO) {
        User user = extractParams(userDTO, User.class);
        QueryWrapper<User> queryWrapper = Wrappers.query(user);
        Page<User> userPage = userService.page(convertToPage(userDTO), queryWrapper);
        Link link = linkTo(virtualInstance.getUser(userDTO));
        return RestStatus.success(convertToPageDTO(userPage), link);
    }


    @PostMapping
    public RestStatus<User> addUser(UserDTO userDTO) {
        User user = extractParams(userDTO, User.class);
        user = userService.addUser(user);
        return RestStatus.success(user);
    }

    @PutMapping
    public RestStatus<User> updateUser(UserDTO userDTO) {
        User user = extractParams(userDTO, User.class);
        user = userService.updateUser(user);
        return RestStatus.success(user);
    }

    @DeleteMapping
    public RestStatus<String> delUser(String userId) {
        userService.removeById(userId);
        return RestStatus.success();
    }


    @PostMapping("/role")
    public RestStatus<String> addUserRole(String userId, String... roleId) {
        List<UserRole> userRoleList = Stream.of(roleId).map(temp -> new UserRole(userId, temp)).collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
        return RestStatus.success();
    }

    @DeleteMapping("/role")
    public RestStatus<String> delUserRole(String userId, String... roleId) {
        QueryWrapper<UserRole> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId);
        if (Objects.nonNull(roleId)) {
            queryWrapper.in("role_id", (Object[]) roleId);
        }
        userRoleService.remove(queryWrapper);
        return RestStatus.success();
    }


    @PostMapping("/resource")
    public RestStatus<String> addUserResource(String userId, String... resourceId) {
        List<UserResource> userRoleList = Stream.of(resourceId).map(temp -> new UserResource(userId, temp)).collect(Collectors.toList());
        userResourceService.saveBatch(userRoleList);
        return RestStatus.success();
    }

    @DeleteMapping("/resource")
    public RestStatus<String> delUserResource(String userId, String... resourceId) {
        QueryWrapper<UserResource> queryWrapper = Wrappers.query();
        queryWrapper.eq("user_id", userId);
        if (Objects.nonNull(resourceId)) {
            queryWrapper.in("resource_id", (Object[]) resourceId);
        }
        userResourceService.remove(queryWrapper);
        return RestStatus.success();
    }


}
