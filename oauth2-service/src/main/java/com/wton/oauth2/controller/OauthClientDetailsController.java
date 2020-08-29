package com.wton.oauth2.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wton.oauth2.entity.OauthClientDetails;
import com.wton.oauth2.extend.RestStatus;
import com.wton.oauth2.service.IOauthClientDetailsService;
import io.swagger.annotations.ApiImplicitParam;
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
@RequestMapping("/oauth2/oauth-client-details")
public class OauthClientDetailsController extends AbstractBaseController {

    private final IOauthClientDetailsService oauthClientDetailsService;

    public OauthClientDetailsController(IOauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    @GetMapping
    public RestStatus<List<OauthClientDetails>> getOauthClientDetail(OauthClientDetails oauthClientDetails) {
        OauthClientDetails clientDetails = extractParams(oauthClientDetails, OauthClientDetails.class);
        QueryWrapper<OauthClientDetails> queryWrapper = Wrappers.query(clientDetails);
        List<OauthClientDetails> oauthClientDetailsList = oauthClientDetailsService.list(queryWrapper);
        return RestStatus.success(oauthClientDetailsList);
    }

    //
    @ApiImplicitParam(name = "authorizedGrantTypes", value = "授权类型", allowableValues = "authorization_code,implicit,refresh_token,client_credentials,password")
    @PostMapping
    public RestStatus<OauthClientDetails> addOauthClientDetail(OauthClientDetails oauthClientDetails) {
        oauthClientDetails = oauthClientDetailsService.addOauthClientDetail(oauthClientDetails);
        return RestStatus.success(oauthClientDetails);
    }

    @PutMapping
    public RestStatus<OauthClientDetails> updateOauthClientDetail(OauthClientDetails oauthClientDetails) {
        oauthClientDetails = oauthClientDetailsService.updateOauthClientDetail(oauthClientDetails);
        return RestStatus.success(oauthClientDetails);
    }

    @DeleteMapping
    public RestStatus<String> delOauthClientDetail(String... clientId) {
        List<String> clientIdList = Arrays.asList(clientId);
        oauthClientDetailsService.removeByIds(clientIdList);
        return RestStatus.success();
    }


}
