package com.buba.entity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderItem {
    private Integer itemId;         // 订单id
    private Integer bookId;         // 每一项编号
    private String bookName;        // 图书名称
    private Integer bookCount;      // 图书数量
    private BigDecimal price;       // 订单总金额
    private Integer orderId;         // 订单编号
    private Date createTime;         // 创建时间

    public OrderItem() {
    }

    public OrderItem(Integer itemId, Integer bookId, String bookName, Integer bookCount, BigDecimal price, Integer orderId, Date createTime) {
        this.itemId = itemId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookCount = bookCount;
        this.price = price;
        this.orderId = orderId;
        this.createTime = createTime;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookCount=" + bookCount +
                ", price=" + price +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
