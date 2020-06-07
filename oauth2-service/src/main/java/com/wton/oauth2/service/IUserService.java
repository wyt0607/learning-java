package com.wton.oauth2.service;

import com.wton.oauth2.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wton
 * @since 2020-04-04
 */
public interface IUserService extends IService<User> {

    List<GrantedAuthority> getUserAuthority(String username, String password);

    User addUser(User user);

    User updateUser(User user);
}
