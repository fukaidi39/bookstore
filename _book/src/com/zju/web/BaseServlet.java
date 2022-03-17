package com.zju.web; /**
 * @author godfu
 * @Date:2022/3/15-21:22
 */

import com.zju.service.UserService;
import com.zju.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //暴露密码，故不用
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        try {
            //利用action鉴别字符串所对应的业务方法，反射获取方法对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            //调用目标业务方法，this是当前类对象的实例
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
