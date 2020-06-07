package com.wton.oauth2.service;

import com.wton.oauth2.entity.OauthClientDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
public interface IOauthClientDetailsService extends IService<OauthClientDetails> {

    OauthClientDetails addOauthClientDetail(OauthClientDetails oauthClientDetails);

    OauthClientDetails updateOauthClientDetail(OauthClientDetails oauthClientDetails);
}
