package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.OauthRefreshToken;
import com.wton.oauth2.mapper.OauthRefreshTokenMapper;
import com.wton.oauth2.service.IOauthRefreshTokenService;
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
public class OauthRefreshTokenServiceImpl extends ServiceImpl<OauthRefreshTokenMapper, OauthRefreshToken> implements IOauthRefreshTokenService {

}
