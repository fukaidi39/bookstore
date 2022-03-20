package com.zju.test;

import com.zju.dao.OrderDao;
import com.zju.dao.impl.OrderDaoImpl;
import com.zju.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author godfu
 * @Date:2022/3/20-12:56
 */
public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = sdf.format(new Date());
        orderDao.saveOrder(new Order("1234567", dateTime, new BigDecimal(100), 0, 1));
    }

    @Test
    public void queryOrders() {
        Iterator<Order> iterator = orderDao.queryOrders().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}