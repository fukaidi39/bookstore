package com.zju.dao.impl;

import com.zju.dao.BaseDao;
import com.zju.dao.OrderDao;
import com.zju.pojo.Order;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/20-11:34
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    //查询才要起别名，确保查询出来的结果集合bean的属性值之间做映射
    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`, `create_time`, `price`, `status`, `user_id`) values(?,?,?,?,?)";
        return update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId from t_order";
        return queryForList(sql, Order.class);
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql = "update t_order set `status`= ? where order_id = ?";
        return update(sql, status, orderId);
    }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql = "select `order_id` orderId, `create_time` createTime, `price`, `status`, `user_id` userId from t_order where `user_id`=?";
        return queryForList(sql, Order.class, userId);
    }
}
