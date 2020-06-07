package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.OauthCode;
import com.wton.oauth2.mapper.OauthCodeMapper;
import com.wton.oauth2.service.IOauthCodeService;
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
public class OauthCodeServiceImpl extends ServiceImpl<OauthCodeMapper, OauthCode> implements IOauthCodeService {

}
