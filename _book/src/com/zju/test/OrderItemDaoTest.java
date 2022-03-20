package com.zju.test;

import com.zju.dao.OrderItemDao;
import com.zju.dao.impl.OrderItemDaoImpl;
import com.zju.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author godfu
 * @Date:2022/3/20-13:00
 */
public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "Java从入门到精通", 1, new BigDecimal(100),new BigDecimal(100), "123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "JavaScript从入门到精通", 2, new BigDecimal(100),new BigDecimal(200), "123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Netty入门", 1, new BigDecimal(100),new BigDecimal(100), "123456"));
    }
}