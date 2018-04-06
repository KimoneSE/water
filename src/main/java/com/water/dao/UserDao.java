package com.water.dao;

import com.water.entity.User;
import java.util.List;

/**
 * Created by asus1 on 2017/7/19.
 */
public interface UserDao extends DaoUtil<User,String> {
    public User findUserByNumber(String number);

    public List<User> findAll();
}
