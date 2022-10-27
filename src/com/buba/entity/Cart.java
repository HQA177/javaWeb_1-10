package com.buba.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer,CartItem> cartItemMap;  // 购物车中购物车项的集合，这个Map集合中的 key是Book的id
    private BigDecimal totalMoney;                  // 购物车的总金额
    private Integer totalCount;                 // 购物车中购物项的数量
    private Integer totalBookCount;             // 购物车书本的总数量，而不是购物车项的数量

    public Cart() {
    }

    // 购物车图书的总数
    public Integer getTotalBookCount() {
        totalBookCount = 0;
        if (cartItemMap != null && cartItemMap.size()  > 0){
            for (CartItem cartItem : cartItemMap.values()){
                totalBookCount += cartItem.getBookCount();
            }
        }
        return totalBookCount;
    }

    public void setTotalBookCount(Integer totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Cart(Map<Integer, CartItem> cartItemMap, BigDecimal totalMoney, Integer totalCount) {
        this.cartItemMap = cartItemMap;
        this.totalMoney = totalMoney;
        this.totalCount = totalCount;
    }

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    public BigDecimal getTotalMoney() {
        BigDecimal totalMoney = new BigDecimal(0);
        if (cartItemMap != null && cartItemMap.size() > 0){
            Set<Map.Entry<Integer, CartItem>> entries = cartItemMap.entrySet();
            for (Map.Entry<Integer, CartItem> cartItemEntry : entries) {
                CartItem cartItem = cartItemEntry.getValue();
                totalMoney = cartItem.getBook().getPrice().multiply(new BigDecimal(cartItem.getBookCount())).add(totalMoney);
            }
        }
        return totalMoney;
    }

    public Integer getTotalCount() {
        BigDecimal totalMoney = new BigDecimal(0);
        if (cartItemMap != null && cartItemMap.size() > 0){
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }
}
