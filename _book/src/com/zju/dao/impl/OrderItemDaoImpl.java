package com.zju.dao.impl;

import com.zju.dao.BaseDao;
import com.zju.dao.OrderItemDao;
import com.zju.pojo.Order;
import com.zju.pojo.OrderItem;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/20-11:35
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`, `count`, `price`, `total_price`, `order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemsByOrderId(String orderId) {
        String sql = "select `id`, `name`, `count`, `price`, `total_price` totalPrice, `order_id` orderId " +
                "from t_order_item where `order_id`=?";
        return queryForList(sql,OrderItem.class, orderId);
    }
}
