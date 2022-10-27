package com.buba.dao.Impl;

import com.buba.dao.BookDao;
import com.buba.entity.Book;
import com.buba.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BookDaoImpl implements BookDao {
    private JdbcTemplate jdbc = new JdbcTemplate(JDBCUtils.getDateSource());
    @Override
    public List<Book> books(Integer min, Integer max, Integer pageNo) {
        String sql = "select * from t_book where price between ? and ? limit ?,10";
        List<Book> books = jdbc.query(sql,new BeanPropertyRowMapper<>(Book.class),min,max,(pageNo-1)*10);
        return books;
    }

    @Override
    public int getBookCount(Integer min, Integer max ) {
        String sql = "select count(*) from t_book where price between ? and ?";
        int date = jdbc.queryForObject(sql,Integer.class,min,max);
        return date;
    }

    @Override
    public Double maxPrice() {
        String sql = "select max(price) from t_book";
        double date = jdbc.queryForObject(sql,Double.class);
        return date;
    }

    @Override
    public int delBook(Integer id) {
        String sql = "delete from t_book where book_id = ?";
        int date = jdbc.update(sql,id);
        return date;
    }

    @Override
    public Book skipEditBook(Integer id) {
        String sql = "select * from t_book where book_id = ?";
        Book book = jdbc.queryForObject(sql,new BeanPropertyRowMapper<>(Book.class),id);
        return book;
    }

    @Override
    public int editBook(Book book) {
        String sql = "update t_book set book_name = ?,price = ?,author = ?,sales = ?,stock = ? where book_id = ?";
        int date = jdbc.update(sql,book.getBookName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock(),book.getBookId());
        return date;
    }

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(img_path,book_name,price,author,sales,stock) value(?,?,?,?,?,?)";
        int date = jdbc.update(sql,book.getImgPath(),book.getBookName(),book.getPrice(),book.getAuthor(),book.getSales(),book.getStock());
        return date;
    }
}
