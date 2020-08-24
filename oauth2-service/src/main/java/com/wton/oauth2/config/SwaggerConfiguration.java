package com.wton.oauth2.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wton.oauth2.controller"))
                /*  .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))*/
                .paths(PathSelectors.any())
                .build()
                .groupName("oauth2-api")
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, customResponseMessage())
                .globalResponseMessage(RequestMethod.POST, customResponseMessage())
                .globalResponseMessage(RequestMethod.DELETE, customResponseMessage())
                .globalResponseMessage(RequestMethod.PATCH, customResponseMessage())
                .securitySchemes(Collections.singletonList((new ApiKey("jwt", HttpHeaders.AUTHORIZATION, In.HEADER.name()))))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("jwt", authorizationScopes));
    }

    @Bean
    public List<ResponseMessage> customResponseMessage() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(200)
                        .message("客户端请求数据成功！")
                        .build(),
                new ResponseMessageBuilder()
                        .code(201)
                        .message("客户端新建或修改数据成功！")
                        .build(),
                new ResponseMessageBuilder()
                        .code(202)
                        .message("请求已经进入后台排队（异步任务")
                        .build(),
                new ResponseMessageBuilder()
                        .code(204)
                        .message("客户端请求删除数据成功。")
                        .build(),
                new ResponseMessageBuilder()
                        .code(400)
                        .message("客户端发出的请求有错误，服务器没有进行新建或修改数据的操作。")
                        .build(),
                new ResponseMessageBuilder()
                        .code(401)
                        .message("客户端没有权限（令牌、用户名、密码错误）")
                        .build(),
                new ResponseMessageBuilder()
                        .code(403)
                        .message("客户端得到授权，但是访问是被禁止的。")
                        .build(),
                new ResponseMessageBuilder()
                        .code(404)
                        .message("客户端发出的请求针对的是不存在的记录，服务器没有进行操作")
                        .build(),
                new ResponseMessageBuilder()
                        .code(406)
                        .message("客户端请求的格式不可得")
                        .build(),
                new ResponseMessageBuilder()
                        .code(410)
                        .message("客户端请求的资源被永久删除，且不会再得到的")
                        .build(),
                new ResponseMessageBuilder()
                        .code(422)
                        .message("当创建一个对象时，发生一个验证错误")
                        .build(),
                new ResponseMessageBuilder()
                        .code(500)
                        .message("服务器发生错误")
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .version("1.0").build();
    }


}
