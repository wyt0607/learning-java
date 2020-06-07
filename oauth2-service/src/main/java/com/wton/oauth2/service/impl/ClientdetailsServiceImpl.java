package com.wton.oauth2.service.impl;

import com.wton.oauth2.entity.Clientdetails;
import com.wton.oauth2.mapper.ClientdetailsMapper;
import com.wton.oauth2.service.IClientdetailsService;
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
public class ClientdetailsServiceImpl extends ServiceImpl<ClientdetailsMapper, Clientdetails> implements IClientdetailsService {

}
