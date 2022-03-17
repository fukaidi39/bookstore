package com.zju.web; /**
 * @author godfu
 * @Date:2022/3/17-20:47
 */

import com.zju.pojo.Book;
import com.zju.pojo.Page;
import com.zju.service.BookService;
import com.zju.service.impl.BookServiceImpl;
import com.zju.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ClientBookServlet", value = "/Client/BookServlet")
public class ClientBookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize,没传值时使用默认值
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize)得到page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        //设置分页访问路径
        page.setUrl("Client/BookServlet?action=page");
        //3.保存page对象到request域中
        request.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

    protected void pageByPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize,min,max没传值时使用默认值
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(request.getParameter("min"),0);
        int max = WebUtils.parseInt(request.getParameter("max"),Integer.MAX_VALUE);
        //2.调用BookService.page(pageNo,pageSize)得到page对象
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        //设置分页访问路径
        StringBuilder url = new StringBuilder("Client/BookServlet?action=pageByPrice");
        //如果有最小价格的参数，追加到分页条的地址参数中
        if (request.getParameter("min") != null){
            url.append("&min=").append(request.getParameter("min"));
        }
        //如果有最大价格的参数，追加到分页条的地址参数中
        if (request.getParameter("max") != null){
            url.append("&max=").append(request.getParameter("max"));
        }
        page.setUrl(url.toString());
        //3.保存page对象到request域中
        request.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

}
