package com.wton.oauth2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wton.oauth2.dto.BaseDTO;
import com.wton.oauth2.dto.PageDTO;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractBaseController {

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

    protected <T> PageDTO<T> getPageDTO(Page<T> page) {
        PageDTO<T> pageDTO = new PageDTO<T>();
        BeanUtils.copyProperties(page, pageDTO);
        return pageDTO;
    }


    protected <T> Page<T> getPage(BaseDTO baseDTO) {
        Integer current = baseDTO.getCurrent();
        Integer size = baseDTO.getSize();
        if (Objects.nonNull(current) && Objects.nonNull(size)) {
            return new Page<>(current, size);
        }
        return new Page<>();
    }
}
