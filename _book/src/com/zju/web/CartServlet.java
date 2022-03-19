package com.zju.web; /**
 * @author godfu
 * @Date:2022/3/18-21:35
 */

import com.zju.pojo.Book;
import com.zju.pojo.Cart;
import com.zju.pojo.CartItem;
import com.zju.service.BookService;
import com.zju.service.impl.BookServiceImpl;
import com.zju.utils.WebUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "CartServlet", value = "/CartServlet")
public class CartServlet extends BaseServlet {
    /**
     * 加入购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookService bookService = new BookServiceImpl();
        //获取请求的参数 id
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //调用bookService.queryBookById(id)得到图书的信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem)添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart == null){
            //第一次创建购物车
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        //将最后一个添加的商品保存到session域中
        request.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回原页面,http协议中有个请求头，会把当前地址栏地址发给服务器
        response.sendRedirect(request.getHeader("Referer"));
    }

    /**
     * 删除商品项
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            cart.deleteItem(id);
            //重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null){
            cart.clear();
            //重定向回原来购物车展示页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求的参数，商品编号及数量
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        int count = WebUtils.parseInt(request.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if(cart != null){
            //修改商品数量
            cart.updateCount(id,count);
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

}
