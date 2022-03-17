package com.zju.test;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.zju.dao.UserDao;
import com.zju.dao.impl.UserDaoImpl;
import com.zju.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author godfu
 * @Date:2022/3/10-20:55
 */
public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("admin"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsernameAndPassword("admin","admin") == null){
            System.out.println("登录失败,用户名或密码错误");
        }else{
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(null ,"fkd", "123456", "1138@qq.com")));
    }
}