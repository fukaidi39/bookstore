package com.zju.filter; /**
 * @author godfu
 * @Date:2022/3/21-16:30
 */

import com.zju.utils.JdbcUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "TransactionFilter")
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
            //提交事务并释放资源
            JdbcUtils.commitAndClose();
        } catch (Exception e) {
            //回滚事务
            JdbcUtils.rollbackAndClose();
            e.printStackTrace();
            //把异常继续抛给Tomcat服务器，做统一异常管理展示
            throw new RuntimeException(e);
        }
    }
}
