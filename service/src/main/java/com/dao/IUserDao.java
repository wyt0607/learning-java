package com.dao;

import com.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by WTON on 2017/5/16.
 */
@Transactional
public interface IUserDao extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findById(Integer id);

    User findByUsernameAndPassword(String username, String password);
}
