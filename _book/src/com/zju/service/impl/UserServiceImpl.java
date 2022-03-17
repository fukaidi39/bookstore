package com.zju.service.impl;

import com.zju.dao.UserDao;
import com.zju.dao.impl.UserDaoImpl;
import com.zju.pojo.User;
import com.zju.service.UserService;

/**
 * @author godfu
 * @Date:2022/3/10-21:52
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao = new UserDaoImpl();
    @Override
    public int registerUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public User login(String username, String password) {
        return userDao.queryUserByUsernameAndPassword(username, password);
    }

    @Override
    public boolean existsUser(String username) {
        if(userDao.queryUserByUsername(username) == null){
            return false;
        }
            return true;

    }
}
