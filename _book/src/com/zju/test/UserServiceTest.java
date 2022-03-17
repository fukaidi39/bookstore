package com.zju.test;

import com.zju.pojo.User;
import com.zju.service.UserService;
import com.zju.service.impl.UserServiceImpl;
import org.junit.Test;


/**
 * @author godfu
 * @Date:2022/3/10-22:01
 */
public class UserServiceTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void registerUser() {
        System.out.println(userService.registerUser(new User(null, "jsy", "123456", "1138@126.com")));
    }

    @Test
    public void login() {
        System.out.println(userService.login("fkd","123456"));
    }

    @Test
    public void existsUser() {
        System.out.println(userService.existsUser("fkds"));
    }
}