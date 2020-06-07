package com.wton.oauth2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wton.oauth2.dto.PageDTO;
import com.wton.oauth2.dto.ResourceDTO;
import com.wton.oauth2.entity.Resource;
import com.wton.oauth2.extend.RestStatus;
import com.wton.oauth2.extend.RestStatusUtil;
import com.wton.oauth2.service.IResourceService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/oauth2/resource")
public class ResourceController extends AbstractBaseController {

    private IResourceService resourceService;

    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @GetMapping
    public RestStatus<PageDTO<Resource>> getResource(ResourceDTO resourceDTO) {
        Resource resource = extractParams(resourceDTO, Resource.class);
        QueryWrapper<Resource> queryWrapper = Wrappers.query(resource);
        Page<Resource> resourcePage = resourceService.page(getPage(resourceDTO), queryWrapper);
        return RestStatusUtil.success(getPageDTO(resourcePage), RestStatusUtil.SUCCESS_DES);
    }

    @PostMapping
    public RestStatus<Resource> addResource(ResourceDTO resourceDTO) {
        Resource resource = extractParams(resourceDTO, Resource.class);
        resource = resourceService.addResource(resource);
        return RestStatusUtil.success(resource, RestStatusUtil.SUCCESS_DES);
    }

    @PutMapping
    public RestStatus<Resource> updateResource(ResourceDTO resourceDTO) {
        Resource resource = extractParams(resourceDTO, Resource.class);
        resource = resourceService.updateResource(resource);
        return RestStatusUtil.success(resource,RestStatusUtil.SUCCESS_DES);
    }

    @DeleteMapping
    public RestStatus<String> delResource(String... id) {
        List<String> idList = Arrays.asList(id);
        resourceService.removeByIds(idList);
        return RestStatusUtil.success();
    }


}
