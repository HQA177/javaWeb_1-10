package com.buba.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private Integer orderId;        // 订单id
    private String orderNumber;    // 订单编号
    private Date createTime;        // 订单创建时间
    private BigDecimal orderPrice;  // 订单金额
    private Integer orderCount;     // 订单数量
    // 0未发货，1已发货，2已签收
    private Integer orderStatus = 0; // 发货状态
    private Integer userId;         // 用户id

    public Order() {
    }

    public Order(String orderNumber,BigDecimal orderPrice, Integer orderCount, Integer orderStatus, Integer userId) {
        this.orderNumber = orderNumber;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public Order(Integer orderId,String orderNumber, Date createTime, BigDecimal orderPrice, Integer orderCount, Integer orderStatus, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", createTime=" + createTime +
                ", orderPrice=" + orderPrice +
                ", orderCount=" + orderCount +
                ", orderStatus=" + orderStatus +
                ", userId=" + userId +
                '}';
    }
}
