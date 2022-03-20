package com.zju.service.impl;

import com.zju.dao.BookDao;
import com.zju.dao.OrderDao;
import com.zju.dao.OrderItemDao;
import com.zju.dao.impl.BookDaoImpl;
import com.zju.dao.impl.OrderDaoImpl;
import com.zju.dao.impl.OrderItemDaoImpl;
import com.zju.pojo.*;
import com.zju.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author godfu
 * @Date:2022/3/20-13:28
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号==唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        //创建订单对象
        Order order = new Order(orderId, dateTime, cart.getTotalPrice(), 0,userId);
        //保存订单到数据库
        orderDao.saveOrder(order);

        //遍历购物车的每一个商品项并转化为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            //获取购物车的每一个商品项
            CartItem cartItem = entry.getValue();
            //转换为每一个订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            //将订单项保存到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemsByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 2);
    }
}
