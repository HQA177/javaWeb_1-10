package com.buba.service.impl;

import com.buba.dao.BookDao;
import com.buba.dao.Impl.BookDaoImpl;
import com.buba.entity.Book;
import com.buba.service.BookService;

import java.util.List;

public class BookServletImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> books(Integer min, Integer max ,Integer pageNo) {
        return bookDao.books(min,max,pageNo);
    }

    @Override
    public int getBookCount(Integer min, Integer max ) {
        return bookDao.getBookCount(min,max);
    }

    @Override
    public Double maxPrice() {
        return bookDao.maxPrice();
    }

    @Override
    public int delBook(Integer id) {
        return bookDao.delBook(id);
    }

    @Override
    public Book skipEditBook(Integer id) {
        return bookDao.skipEditBook(id);
    }

    @Override
    public int editBook(Book book) {
        return bookDao.editBook(book);
    }

    @Override
    public int addBook(Book book) {
        return bookDao.addBook(book);
    }
}
