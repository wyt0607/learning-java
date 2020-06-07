package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.OauthClientToken;
import com.wton.oauth2.mapper.OauthClientTokenMapper;
import com.wton.oauth2.service.IOauthClientTokenService;
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
public class OauthClientTokenServiceImpl extends ServiceImpl<OauthClientTokenMapper, OauthClientToken> implements IOauthClientTokenService {

}
