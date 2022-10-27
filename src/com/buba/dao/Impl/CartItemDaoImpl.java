package com.buba.dao.Impl;

import com.buba.dao.CartItemDao;
import com.buba.entity.Book;
import com.buba.entity.CartItem;
import com.buba.entity.User;
import com.buba.service.BookService;
import com.buba.service.impl.BookServletImpl;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CartItemDaoImpl implements CartItemDao {
    private BookService bookService = new BookServletImpl();
    private JdbcTemplate jdbc = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public int addCartItem(CartItem cartItem) {
        String sql = "insert into t_cart_item values(0,?,?,?)";
        int i = jdbc.update(sql,cartItem.getBook().getBookId(),cartItem.getBookCount(),cartItem.getUser().getUserId());
        return i;
    }

    @Override
    public int updateCartItem(CartItem cartItem) {
        String sql = "update t_cart_item set book_count = ? where cart_id = ?";
        int i = jdbc.update(sql,cartItem.getBookCount(),cartItem.getCartId());
        return i;
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        String sql = "select * from t_cart_item where user_id = ?";
        List<CartItem> list = jdbc.query(sql,new BeanPropertyRowMapper<>(CartItem.class),user.getUserId());
        for (CartItem cartItem : list) {
            Integer bookId = cartItem.getBookId();
            Book book = bookService.skipEditBook(bookId);
            cartItem.setBook(book);
        }
        return list;
    }

    @Override
    public int delCart(Integer id) {
        String sql = "delete from t_cart_item where cart_id = ?";
        int date = jdbc.update(sql,id);
        return date;
    }

    @Override
    public int clearCart(Integer id) {
        String sql = "delete from t_cart_item where user_id = ?";
        int date = jdbc.update(sql,id);
        return date;
    }
}
