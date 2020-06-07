package com.wton.oauth2.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wton.oauth2.entity.OauthClientDetails;
import com.wton.oauth2.mapper.OauthClientDetailsMapper;
import com.wton.oauth2.service.IOauthClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
@Service
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements IOauthClientDetailsService {

    @Override
    public OauthClientDetails addOauthClientDetail(OauthClientDetails oauthClientDetails) {
        if (Objects.isNull(getById(oauthClientDetails.getClientId()))) {
            if (save(oauthClientDetails)) {
                return oauthClientDetails;
            }
        }
        return null;
    }

    @Override
    public OauthClientDetails updateOauthClientDetail(OauthClientDetails oauthClientDetails) {
        if (Objects.nonNull(getById(oauthClientDetails.getClientId()))) {
            if (updateById(oauthClientDetails)) {
                return oauthClientDetails;
            }
        }
        return null;
    }
}
