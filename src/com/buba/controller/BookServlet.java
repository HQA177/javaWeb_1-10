package com.buba.controller;

import com.buba.entity.Book;
import com.buba.service.BookService;
import com.buba.service.impl.BookServletImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class BookServlet extends ViewBaseServlet{
    private IndexServlet indexServlet = new IndexServlet();
    private BookService bookService = new BookServletImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 图书管理页面
        if(req.getParameter("user").equals("book_manager")){
            this.librarian(req,resp);
            processTemplate("/pages/manager/book_manager",req,resp);
        }
        // 删除图书方法
        if (req.getParameter("user").equals("delBook")){
            this.delBook(req,resp);
        }
        // 编辑图书跳转
        if(req.getParameter("user").equals("skipEditBook")){
            this.skipEditBook(req,resp);
        }
        // 编辑图书方法
        if (req.getParameter("user").equals("editBook")){
            this.editBook(req,resp);
        }
        // 添加图书跳转
        if(req.getParameter("user").equals("skipAddBook")){
            processTemplate("/pages/manager/book_add",req,resp);
        }
        // 添加图书方法
        if(req.getParameter("user").equals("addBook")){
            this.addBook(req, resp);
        }
    }

    // 后台管理页面渲染图书列表
    public void librarian(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Double maxPrice = bookService.maxPrice();
        indexServlet.findBooks(0,(int)Math.ceil(maxPrice),req,resp);
    }

    // 后台管理页面删除图书列表
    public void delBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        System.out.println("前"+id);
        int i = bookService.delBook(Integer.parseInt(id));
        System.out.println("后"+i);

        Double aDouble = bookService.maxPrice();
        String pageNo = req.getParameter("pageNo");


        List<Book> list = bookService.books(0,(int)(aDouble +1),Integer.parseInt(pageNo));
        System.out.println(list);
        if (list.isEmpty()){
            resp.sendRedirect("bookHtml?user=book_manager&pageNo="+(Integer.valueOf(pageNo)-1));
        }else {
            resp.sendRedirect("bookHtml?user=book_manager&pageNo="+pageNo);
        }
    }

    // 后台管理页面跳转修改页面获取要修改的数据
    public void skipEditBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.skipEditBook(Integer.parseInt(id));
        req.setAttribute("item",book);
        req.getServletContext().setAttribute("bookName",id);
        processTemplate("/pages/manager/book_edit",req,resp);
    }

    // 后台管理页面修改数据
    public void editBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String author = req.getParameter("author");
        String sales = req.getParameter("sales");
        String stock = req.getParameter("stock");
        Object attribute = req.getServletContext().getAttribute("bookName");
        Book book = new Book();
        book.setBookId(Integer.parseInt(attribute.toString()));
        book.setBookName(name);
        book.setPrice(new BigDecimal(price));
        book.setAuthor(author);
        book.setSales(Integer.parseInt(sales));
        book.setStock(Integer.parseInt(stock));
        bookService.editBook(book);
        resp.sendRedirect("bookHtml?user=book_manager");
    }

    // 后台管理页面添加数据
    public void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(ServletFileUpload.isMultipartContent(req)){
            //创建工厂实现类FileItemFactory
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload=  new ServletFileUpload(fileItemFactory );
            //解析上传的数据，得到每一个表单项FileItem

            try {
                List<FileItem> items  = servletFileUpload.parseRequest(req);

                FileItem fileItem = items.get(0);
                // 文件类型
                // 获取文件后缀名
                String imgtype = fileItem.getName().substring(fileItem.getName().lastIndexOf("."));
                // 给文件重新命名防止重复
                String imgName = UUID.randomUUID() + imgtype;
                System.out.println();
                String path="E:\\2110A\\javaWeb_1-10\\web\\static\\uploads";
                // 将上传的文件保存到服务器
                fileItem.write(new File(path, imgName));

                Book book = new Book();
                // 把服务器中的路径添加到数据库中
                String sqlPath=null;
                sqlPath = "static/uploads/" + imgName;
                book.setImgPath(sqlPath);
                book.setBookName(items.get(1).getString("UTF-8"));
                book.setPrice(new BigDecimal(items.get(2).getString("UTF-8")));
                book.setAuthor(items.get(3).getString("UTF-8"));
                book.setSales(Integer.valueOf(items.get(4).getString("UTF-8")));
                book.setStock(Integer.valueOf(items.get(5).getString("UTF-8")));
                System.out.println(book.toString());
                bookService.addBook(book);

                // 将参数覆盖到会话域,以便修改界面使用
                HttpSession session = req.getSession();
                session.setAttribute("imgPath",book.getImgPath());
                session.setAttribute("name",book.getBookName());
                session.setAttribute("price",book.getPrice());
                session.setAttribute("author",book.getAuthor());
                session.setAttribute("sales",book.getSales());
                session.setAttribute("stock",book.getStock());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("bookHtml?user=book_manager");
    }
}
