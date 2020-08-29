package com.wton.oauth2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wton.oauth2.dto.PageDTO;
import com.wton.oauth2.dto.ResourceDTO;
import com.wton.oauth2.entity.Resource;
import com.wton.oauth2.extend.RestStatus;
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
        Page<Resource> resourcePage = resourceService.page(convertToPage(resourceDTO), queryWrapper);
        return RestStatus.success(convertToPageDTO(resourcePage));
    }

    @PostMapping
    public RestStatus<Resource> addResource(ResourceDTO resourceDTO) {
        Resource resource = extractParams(resourceDTO, Resource.class);
        resource = resourceService.addResource(resource);
        return RestStatus.success(resource);
    }

    @PutMapping
    public RestStatus<Resource> updateResource(ResourceDTO resourceDTO) {
        Resource resource = extractParams(resourceDTO, Resource.class);
        resource = resourceService.updateResource(resource);
        return RestStatus.success(resource,RestStatus.SUCCESS_MSG);
    }

    @DeleteMapping
    public RestStatus<String> delResource(String... id) {
        List<String> idList = Arrays.asList(id);
        resourceService.removeByIds(idList);
        return RestStatus.success();
    }


}
