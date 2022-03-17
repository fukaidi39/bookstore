package com.zju.dao;

import com.zju.pojo.User;

/**
 * @author godfu
 * @Date:2022/3/10-19:56
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息(注册)
     * @param username 用户名
     * @return 查询到的用户对象,如果没有返回null
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息(登录)
     * @param username 用户名
     * @param password 密码
     * @return 返回null说明用户名或者密码错误
     */
    public User queryUserByUsernameAndPassword(String username, String password);
    /**
     * 保存用户信息
     * @param user 用户对象
     * @return 返回1说明保存成功,返回-1表示操作失败
     */
    public int saveUser(User user);
}
