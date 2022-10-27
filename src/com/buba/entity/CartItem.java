package com.buba.entity;


public class CartItem {
    private Integer cartId;
    private Book book;
    private Integer bookCount;
    private User user;
    private Integer userId;
    private Integer bookId;
    public CartItem() {
    }

    public CartItem(Integer cartId, Integer bookCount) {
        this.cartId = cartId;
        this.bookCount = bookCount;
    }

    public CartItem(Book book, Integer bookCount, User user) {
        this.book = book;
        this.bookCount = bookCount;
        this.user = user;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartId=" + cartId +
                ", book=" + book +
                ", bookCount=" + bookCount +
                ", user=" + user +
                ", userId=" + userId +
                ", bookId=" + bookId +
                '}';
    }
}
