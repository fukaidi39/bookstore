package com.zju.service.impl;

import com.zju.dao.BookDao;
import com.zju.dao.impl.BookDaoImpl;
import com.zju.pojo.Book;
import com.zju.service.BookService;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/16-16:00
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}
