package com.buba.dao;

import com.buba.entity.CartItem;
import com.buba.entity.User;

import java.util.List;

public interface CartItemDao {
    // 新增购物车项
    int addCartItem(CartItem cartItem);
    // 修改特定的购物车项
    int  updateCartItem(CartItem cartItem);
    // 获取特定用户的所有购物车项
    List<CartItem> getCartItemList(User user);
    // 删除购物车项
    int delCart(Integer id);
    // 清空购物车项
    int clearCart(Integer id);
}
