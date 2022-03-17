package com.zju.dao.impl;

import com.zju.dao.BaseDao;
import com.zju.dao.UserDao;
import com.zju.pojo.User;

/**
 * @author godfu
 * @Date:2022/3/10-20:06
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username=? ";
        return queryForOne(sql,User.class,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select `id`, `username`, `password`, `email` from t_user where username=? and password=?";
        return queryForOne(sql,User.class,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
