package com.buba.service;

import com.buba.entity.Book;

import java.util.List;

public interface BookService {

    // 查询图书
    List<Book> books(Integer min, Integer max ,Integer pageNo);

    // 查询库存总记录条数
    int getBookCount(Integer min, Integer max);

    // 主页筛选：查询最大价格
    Double maxPrice();

    // 删除图书
    int delBook(Integer id);

    // 修改图书跳转页面获取数据
    Book skipEditBook(Integer id);

    // 修改图书方法
    int editBook(Book book);

    // 添加图书
    int addBook(Book book);
}
