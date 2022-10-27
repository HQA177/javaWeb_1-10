package com.buba.entity;

import java.math.BigDecimal;

public class OrderItem {
    private Integer itemId;         // 每一项编号
    private String bookName;        // 图书名称
    private Integer bookCount;      // 图书数量
    private BigDecimal price;       // 订单总金额
    private String orderId;         // 订单编号

    public OrderItem() {
    }

    public OrderItem(Integer itemId, String bookName, Integer bookCount, BigDecimal price, String orderId) {
        this.itemId = itemId;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.price = price;
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", price=" + price +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
