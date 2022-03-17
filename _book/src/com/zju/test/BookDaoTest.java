package com.zju.test;

import com.zju.dao.BaseDao;
import com.zju.dao.BookDao;
import com.zju.dao.impl.BookDaoImpl;
import com.zju.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author godfu
 * @Date:2022/3/16-14:32
 */
public class BookDaoTest {
    BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "三体", "刘慈欣", new BigDecimal(50), 5000, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21,"三十四体", "刘慈欣", new BigDecimal(50), 5000, 100, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookDao.queryBooks();
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        for (Book item : bookDao.queryForPageItems(8, 4)) {
            System.out.println(item);
        }

    }
}