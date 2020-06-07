package com.wton.oauth2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wton.oauth2.dto.PageDTO;
import com.wton.oauth2.dto.RoleDTO;
import com.wton.oauth2.entity.Role;
import com.wton.oauth2.entity.RoleResource;
import com.wton.oauth2.extend.RestStatus;
import com.wton.oauth2.extend.RestStatusUtil;
import com.wton.oauth2.service.IRoleResourceService;
import com.wton.oauth2.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RequestMapping("/oauth2/role")
public class RoleController extends AbstractBaseController {

    private IRoleService roleService;
    private IRoleResourceService roleResourceService;

    public RoleController(IRoleService roleService, IRoleResourceService roleResourceService) {
        this.roleService = roleService;
        this.roleResourceService = roleResourceService;
    }

    @GetMapping
    public RestStatus<PageDTO<Role>> getRole(RoleDTO roleDTO) {
        Role role = extractParams(roleDTO, Role.class);
        QueryWrapper<Role> queryWrapper = Wrappers.query(role);
        Page<Role> rolePage = roleService.page(getPage(roleDTO), queryWrapper);
        return RestStatusUtil.success(getPageDTO(rolePage), RestStatusUtil.SUCCESS_DES);
    }

    @PostMapping
    public RestStatus<Role> addRole(RoleDTO roleDTO) {
        Role role = extractParams(roleDTO, Role.class);
        role = roleService.addRole(role);
        return RestStatusUtil.success(role, RestStatusUtil.SUCCESS_DES);
    }

    @PutMapping
    public RestStatus<Role> updateRole(RoleDTO roleDTO) {
        Role role = extractParams(roleDTO, Role.class);
        role = roleService.updateRole(role);
        return RestStatusUtil.success(role, RestStatusUtil.SUCCESS_DES);
    }

    @DeleteMapping
    public RestStatus<String> delRole(String... id) {
        List<String> idList = Arrays.asList(id);
        roleService.removeByIds(idList);
        return RestStatusUtil.success();
    }


    @PostMapping("/resource")
    public RestStatus<String> addRoleResource(String roleId, String... resourceId) {
        List<RoleResource> roleResourceList = Stream.of(resourceId).map(temp -> new RoleResource(roleId, temp)).collect(Collectors.toList());
        roleResourceService.saveBatch(roleResourceList);
        return RestStatusUtil.success();
    }

    @DeleteMapping("/resource")
    public RestStatus<String> delRoleResource(String roleId, String... resourceId) {
        QueryWrapper<RoleResource> queryWrapper = Wrappers.query();
        queryWrapper.eq("role_id", roleId);
        if (Objects.nonNull(resourceId)) {
            queryWrapper.in("resource_id", resourceId);
        }
        roleResourceService.remove(queryWrapper);
        return RestStatusUtil.success();
    }

}
