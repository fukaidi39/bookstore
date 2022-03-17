package com.zju.web; /**
 * @author godfu
 * @Date:2022/3/16-16:52
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
import java.util.List;

@WebServlet(name = "BookServlet", value = "/manager/BookServlet")
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.通过BookService查询全部图书
        List<Book> bookList = bookService.queryBooks();
        //2.把全部图书保存到request域中
        request.setAttribute("bookList", bookList);
        //3.请求转发到book_manager.jsp
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取请求的参数pageNo和pageSize,没传值时使用默认值
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize)得到page对象
        Page<Book> page = bookService.page(pageNo,pageSize);
        //3.保存page对象到request域中
        request.setAttribute("page", page);
        //4.请求转发到pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    protected void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
        //使返回后显示的页面永远在最后一页
        pageNo+=1;
        //BeanUtil将前端传来的表单按name = value的形式自动给Bean对象封装
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //调用BookService.addBook()保存图书入数据库
        bookService.addBook(book);
        //跳回到图书列表页面(/代表http://localhost:8080)
        //用请求转发会产生bug，当用户按下f5会重新发起最后一次请求，所以用重定向两次请求。
        response.sendRedirect(request.getContextPath()+ "/manager/BookServlet?action=page&pageNo="+pageNo);
    }

    protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取删除的id
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        bookService.deleteBookById(id);
        response.sendRedirect("/book/manager/BookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.将请求参数封装成为javaBean对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(), new Book());
        //2.调用bookService.updateBook(Book book)方法
        bookService.updateBook(book);
        //3.请求重定向到图书列表页面
        response.sendRedirect("/book/manager/BookServlet?action=page&pageNo="+request.getParameter("pageNo"));
    }

    /**
     * 将选择修改的数据带到表单中去显示
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = WebUtils.parseInt(request.getParameter("id"),0);
        //根据id查询数据
        Book book = bookService.queryBookById(id);
        //将对象保存到request域当中
        request.setAttribute("book", book);
        //请求转发到/pages/manager/book_edit.jsp
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
    }
}
