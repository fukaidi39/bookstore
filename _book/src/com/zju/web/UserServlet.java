package com.zju.web; /**
 * @author godfu
 * @Date:2022/3/15-20:19
 */

import com.zju.pojo.User;
import com.zju.service.UserService;
import com.zju.service.impl.UserServiceImpl;
import com.zju.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends BaseServlet {
    /**
     * 处理登录业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //调用UserService.login()方法判断是否登录成功
        if(userService.login(username,password) == null){
            //把错误信息和回显表单保存到Request域中
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request,response);
        }
    }

    /**
     * 处理注册业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数信息
        String code = request.getParameter("code");
        //获取请求参数注入到javabean对象中
        User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());

        //判断验证码是否正确
        if(code.equalsIgnoreCase("6n6np")){
            //判断用户名是否存在
            if(userService.existsUser(user.getUsername())){
                //用户存在
                request.setAttribute("msg", "用户名已存在");
                request.setAttribute("username", user.getUsername());
                request.setAttribute("password", user.getPassword());
                request.setAttribute("email", user.getEmail());
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);;

            }else{
                //用户成功注册
                userService.registerUser(user);
                request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request,response);
            }
        }else{
            //验证码错误
            request.setAttribute("msg", "验证码错误");
            request.setAttribute("username", user.getUsername());
            request.setAttribute("password", user.getPassword());
            request.setAttribute("email", user.getEmail());
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }
    }
}
