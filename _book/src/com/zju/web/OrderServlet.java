package com.zju.web; /**
 * @author godfu
 * @Date:2022/3/20-13:56
 */

import com.zju.pojo.Cart;
import com.zju.pojo.Order;
import com.zju.pojo.OrderItem;
import com.zju.pojo.User;
import com.zju.service.OrderService;
import com.zju.service.impl.OrderServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取Cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        //获取登录的用户对象
        User loginUser = (User) request.getSession().getAttribute("User");
        //检查用户登录状态
        if (loginUser == null){
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
            return;
        }
        Integer userId = loginUser.getId();
        //调用Service层生成订单
        String orderId = orderService.createOrder(cart, userId);
        //将订单保存到session域中
        request.getSession().setAttribute("orderId", orderId);
        response.sendRedirect(request.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /**
     * 管理员查看所有订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service层查看所有订单
        List<Order> orders = orderService.showAllOrders();
        //保存到request域中
        request.setAttribute("orders", orders);
        //请求转发
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request,response);
    }

    /**
     * 发货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从前端获取orderId
        String orderId = request.getParameter("orderId");
        //调用service层修改指定ID的订单项
        orderService.sendOrder(orderId);
        //重定向回页面,重新查看所有订单
        response.sendRedirect(request.getContextPath()+"/OrderServlet?action=showAllOrders");
    }

    /**
     * 查看订单详情
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取订单号
        String orderId = request.getParameter("orderId");
        //调用service方法查询订单详情
        List<OrderItem> orderDetail = orderService.showOrderDetail(orderId);
        //将订单项保存到request域中
        request.setAttribute("orderDetail", orderDetail);
        //请求转发
        request.getRequestDispatcher("/pages/order/orderItem.jsp").forward(request,response);
    }

    /**
     * 查看我的订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取session域中的用户对象
        User user = (User) request.getSession().getAttribute("User");
        if(user == null){
            response.sendRedirect(request.getContextPath()+"/pages/user/login.jsp");
        }else{
            //调用service方法查看我的订单
            List<Order> orderList = orderService.showMyOrders(user.getId());
            //保存到request域中
            request.setAttribute("orderList", orderList);
            //请求转发
            request.getRequestDispatcher("/pages/order/order.jsp").forward(request,response);
        }
    }

    /**
     * 签收订单/确认收货
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void receiveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从前端获取orderId
        String orderId = request.getParameter("orderId");
        //调用service层的方法修改订单状态
        orderService.receiveOrder(orderId);
        //重定向回当前页面查看我的订单
        response.sendRedirect(request.getContextPath()+"/OrderServlet?action=showMyOrders");
    }


}
