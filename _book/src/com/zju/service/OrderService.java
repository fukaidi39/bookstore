package com.zju.service;

import com.zju.pojo.Cart;
import com.zju.pojo.Order;
import com.zju.pojo.OrderItem;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/20-13:05
 */
public interface OrderService {
    /**
     * 清空购物车，创建订单，返回订单号
     * @param cart 购物车
     * @param userId 用户Id
     * @return 订单号
     */

    public String createOrder(Cart cart, Integer userId);

    /**
     * 查询全部订单
     * @return
     */
    public List<Order> showAllOrders();

    /**
     * 发货
     * @param orderId
     */
    public void sendOrder(String orderId);

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    public List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    public List<Order> showMyOrders(Integer userId);

    /**
     * 签收订单/确认收货
     * @param orderId
     */
    public void receiveOrder(String orderId);
}
