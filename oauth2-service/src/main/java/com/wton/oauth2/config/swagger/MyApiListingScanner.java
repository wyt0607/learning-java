package com.wton.oauth2.config.swagger;


import com.google.common.base.Optional;
import com.google.common.collect.Ordering;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiDescriptionBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.Types;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;

import java.util.*;

import static springfox.documentation.schema.ResolvedTypes.modelRefFactory;

@Component
public class MyApiListingScanner implements ApiListingScannerPlugin {

    private final List<ResponseMessage> responseMessages;
    private final DocumentationCache documentationCache;

    public MyApiListingScanner(@Qualifier("customResponseMessage") List<ResponseMessage> responseMessages, DocumentationCache documentationCache) {
        this.responseMessages = responseMessages;
        this.documentationCache = documentationCache;
    }

    @Override
    public List<ApiDescription> apply(DocumentationContext documentationContext) {
        OperationBuilder operationBuilder = new OperationBuilder(new CachingOperationNameGenerator());
        Set<String> tags = new HashSet<>();
        Set<String> produces = new HashSet<>();
        Set<String> consumes = new HashSet<>();

        List<Parameter> parameters = getParameters();
        tags.add("授权");
        produces.add(MediaType.APPLICATION_JSON.getType());
        consumes.add("*/*");
        Operation operation = operationBuilder
                .tags(tags)
                .uniqueId("authtstse")
                .produces(consumes)
                .consumes(produces)
                .summary("token")
                .parameters(parameters)
                .deprecated("false")
                .responseMessages(new HashSet<>(responseMessages))
                .responseModel(new ModelRef("token对象"))
                .method(HttpMethod.POST)
                .build();

        Ordering<Operation> ordering = Ordering.explicit(operation);
        ApiDescriptionBuilder apiDescriptionBuilder = new ApiDescriptionBuilder(ordering);
        List<Operation> objects = new ArrayList<>();
        objects.add(operation);
        return new ArrayList<>(Collections.singletonList(
                apiDescriptionBuilder
                        .groupName(documentationContext.getGroupName())
                        .path("/oauth/token")
                        .operations(objects)
                        .build())
        );
    }

    private List<Parameter> getParameters() {


        Parameter clientId = new ParameterBuilder().name("client_id")
                .description("客户端ID")
                .defaultValue("wton")
                .required(true)
                .modelRef(new ModelRef(Types.typeNameFor(String.class)))
                .parameterType("query")
                .order(0)
                .build();


        AllowableValues allowableValues = new AllowableListValues(Arrays.asList("authorization_code", "implicit", "refresh_token", "client_credentials", "password"), Types.typeNameFor(List.class));


        Parameter grantType = new ParameterBuilder().name("grant_type")
                .description("认证类型")
                .defaultValue("client_credentials")
                .required(true)
                .modelRef(new ModelRef(Types.typeNameFor(String.class), allowableValues))
                .allowableValues(allowableValues)
                .parameterType("query")
                .order(1)
                .build();


        Parameter userId = new ParameterBuilder().name("user_id")
                .description("用户ID")
                .defaultValue("admin")
                .required(true)
                .modelRef(new ModelRef(Types.typeNameFor(String.class)))
                .parameterType("query")
                .order(2)
                .build();


        return Arrays.asList(clientId, grantType, userId);
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return true;
    }
}