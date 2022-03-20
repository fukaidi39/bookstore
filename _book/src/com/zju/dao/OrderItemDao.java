package com.zju.dao;

import com.zju.pojo.Book;
import com.zju.pojo.Order;
import com.zju.pojo.OrderItem;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/20-11:31
 */
public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return
     */
    public int saveOrderItem(OrderItem orderItem);

    /**
     * 根据用户编号查询订单详情
     * @param orderId
     * @return
     */
    public List<OrderItem> queryOrderItemsByOrderId(String orderId);
}
