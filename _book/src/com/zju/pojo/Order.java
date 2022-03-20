package com.zju.pojo;

/**
 * @author godfu
 * @Date:2022/3/20-11:21
 */

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 */
public class Order {
    private String orderId;
    private String createTime;
    private BigDecimal price;
    // 0表示未发货,1表示已发货,2表示已签收
    private Integer status = 0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, String createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
