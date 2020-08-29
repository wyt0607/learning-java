package com.wton.oauth2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wton.oauth2.dto.BaseDTO;
import com.wton.oauth2.dto.PageDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author wton
 */
public abstract class AbstractBaseController {

    /**
     * 生成Link对象
     */
    protected Link linkTo(Object c) {
        return WebMvcLinkBuilder.linkTo(c).withSelfRel();
    }

    /**
     * 生成虚拟实体.
     *
     * @return 虚拟实体
     */
    protected <T> T virtualInstance(Class<T> c) {
        return WebMvcLinkBuilder.methodOn(c);
    }

    /**
     * 过滤"null"和""并提取DTO的参数到具体的实体
     *
     * @return 实体
     */
    protected <T, K> T extractParams(K source, Class<T> c) {
        Class<?> sourceClass = source.getClass();
        Method[] declaredMethods = sourceClass.getDeclaredMethods();
        Stream.of(declaredMethods)
                .filter(method -> method.getName().startsWith("get"))
                .forEach(method -> {
                    try {
                        Object invoke = method.invoke(source);
                        if ("null".equals(invoke) || "".equals(invoke)) {
                            Method setMethod = sourceClass.getMethod(method.getName().replace("get", "set"), method.getReturnType());
                            setMethod.invoke(source, (Object) null);
                        }
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                });

        T target = BeanUtils.instantiateClass(c);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * page 转换 pageDTO
     */
    protected <T> PageDTO<T> convertToPageDTO(Page<T> page) {
        PageDTO<T> pageDTO = new PageDTO<T>();
        BeanUtils.copyProperties(page, pageDTO);
        return pageDTO;
    }


    /**
     * 从DTO中提取page对象
     */
    protected <T> Page<T> convertToPage(BaseDTO baseDTO) {
        Integer current = baseDTO.getCurrent();
        Integer size = baseDTO.getSize();
        if (Objects.nonNull(current) && Objects.nonNull(size)) {
            return new Page<>(current, size);
        }
        return new Page<>();
    }

}
