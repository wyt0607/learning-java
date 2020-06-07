package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.OauthApprovals;
import com.wton.oauth2.mapper.OauthApprovalsMapper;
import com.wton.oauth2.service.IOauthApprovalsService;
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
public class OauthApprovalsServiceImpl extends ServiceImpl<OauthApprovalsMapper, OauthApprovals> implements IOauthApprovalsService {

}
