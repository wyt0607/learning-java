package com.wton.oauth2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wton.oauth2.entity.Resource;
import com.wton.oauth2.mapper.ResourceMapper;
import com.wton.oauth2.service.IResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public Resource addResource(Resource resource) {
        QueryWrapper<Resource> queryWrapper = Wrappers.query();
        queryWrapper.eq("authority", resource.getAuthority());
        if (Objects.isNull(getOne(queryWrapper))) {
            if (save(resource)) {
                return resource;
            }
        }
        return null;
    }

    @Override
    public Resource updateResource(Resource resource) {
        Resource updateResource = getById(resource.getId());
        if (Objects.nonNull(updateResource)) {
            BeanUtils.copyProperties(resource, updateResource);
            if (updateById(updateResource)) {
                return updateResource;
            }
        }
        return null;
    }

}
