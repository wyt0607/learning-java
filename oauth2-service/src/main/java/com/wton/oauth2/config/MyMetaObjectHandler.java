package com.wton.oauth2.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author wton
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        fillStrategy(metaObject, "createId", "system");
        fillStrategy(metaObject, "createBy", "system");
        fillStrategy(metaObject, "createTime", LocalDateTime.now());
        fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fillStrategy(metaObject, "updateId", "system");
        fillStrategy(metaObject, "updateBy", "system");
        fillStrategy(metaObject, "updateTime", LocalDateTime.now());
    }
}