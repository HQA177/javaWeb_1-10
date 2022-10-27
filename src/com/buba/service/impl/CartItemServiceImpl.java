package com.buba.service.impl;

import com.buba.dao.CartItemDao;
import com.buba.dao.Impl.CartItemDaoImpl;
import com.buba.entity.Cart;
import com.buba.entity.CartItem;
import com.buba.entity.User;
import com.buba.service.CartItemService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDao cartItemDao = new CartItemDaoImpl();

    @Override
    public int addCartItem(CartItem cartItem) {
        return cartItemDao.addCartItem(cartItem);
    }

    @Override
    public int updateCartItem(CartItem cartItem) {
        return cartItemDao.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        // 1、如果当前用户的购物车中已经存在这个图书了，那么将购物车中这本图书的数量+1
        // 2、否者，在我的购物车中新增一个这本图书的CartItem，数量是 1
        // 判断当前用户的购物车中是否有这本书的CartItem，有->update  无->add
        if (cart != null){
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null){
                cartItemMap = new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getBookId())){
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getBookId());
                cartItemTemp.setBookCount(cartItemTemp.getBookCount()+1);
                updateCartItem(cartItemTemp);
            }else {
                addCartItem(cartItem);
            }
        }else {  // 连购物车都没有的情况
            addCartItem(cartItem);
        }
    }

    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = cartItemDao.getCartItemList(user);
        Map<Integer,CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList){
            cartItemMap.put(cartItem.getBook().getBookId(),cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        return cart;
    }

    @Override
    public int delCart(Integer id) {
        return cartItemDao.delCart(id);
    }

    @Override
    public int clearCart(Integer id) {
        return cartItemDao.clearCart(id);
    }
}
