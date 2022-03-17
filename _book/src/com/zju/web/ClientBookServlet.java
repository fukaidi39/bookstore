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
        //3.保存page对象到request域中
        request.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/client/index.jsp").forward(request,response);
    }

}
