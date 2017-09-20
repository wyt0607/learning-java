package com.service;

import com.entity.Status;
import com.entity.User;

/**
 * Created by WTON on 2017/5/16.
 */
public interface IUserService {


    User findById(Integer id);

    User register(String userName, String password);

    User login(String userName, String password);
}
