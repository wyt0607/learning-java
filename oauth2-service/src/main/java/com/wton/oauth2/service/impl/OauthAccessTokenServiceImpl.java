package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.OauthAccessToken;
import com.wton.oauth2.mapper.OauthAccessTokenMapper;
import com.wton.oauth2.service.IOauthAccessTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@Service
public class OauthAccessTokenServiceImpl extends ServiceImpl<OauthAccessTokenMapper, OauthAccessToken> implements IOauthAccessTokenService {

}
