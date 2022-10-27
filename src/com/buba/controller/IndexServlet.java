package com.buba.controller;

import com.alibaba.druid.util.StringUtils;
import com.buba.entity.Book;
import com.buba.service.BookService;
import com.buba.service.impl.BookServletImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:SmallTiger
 * Date:2022-09-30
 * Time:08:18
 */
public class IndexServlet extends ViewBaseServlet {
    private BookService bookService = new BookServletImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String min = req.getParameter("min");
        String max = req.getParameter("max");
        System.out.println("开始min："+min);
        System.out.println("开始max："+max);
        double maxPrice = bookService.maxPrice();
        if (min != null && max != null){
            session.setAttribute("min",min);
            session.setAttribute("max",max);
            this.findBooks(Integer.parseInt(min),Integer.parseInt(max),req,resp);
        }else {
            this.findBooks(0,(int)Math.ceil(maxPrice),req,resp);
        }
        processTemplate("index",req,resp);
    }

    public void findBooks(Integer min, Integer max ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = 1;
        String pageNoStr = req.getParameter("pageNo");
        if (!StringUtils.isEmpty(pageNoStr)){
            pageNo = Integer.parseInt(pageNoStr);
        }

        HttpSession session = req.getSession();
        session.setAttribute("pageNo",pageNo);

        // 总记录条数
        int bookCount = bookService.getBookCount(min,max);
        System.out.println("总记录条数"+bookCount);
//        int b = (int) bookCount;
        // 总页数
        int pageCount = (bookCount+10-1)/10;
//        int p = (int) pageCount;
        ArrayList<Object> objects = new ArrayList<>();
            for (int i = 0; i < pageCount; i++) {
            objects.add(i+1);
        }
        session.setAttribute("object",objects);
        /*
         * 总记录条数     总页数
         * 1             1
         * 10            1
         * 16            2
         * bookCount     (bookCount+10-1)/10
         * */

        session.setAttribute("pageCount",pageCount);
        // 总数量取整
//        int i = Integer.parseInt(String.valueOf(bookCount));
        session.setAttribute("bookCount",bookCount);

        List<Book> books = bookService.books(min,max,pageNo);
        req.setAttribute("bookList",books);

    }
}
