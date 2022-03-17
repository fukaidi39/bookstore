package com.zju.test;

import com.zju.pojo.Book;
import com.zju.service.BookService;
import com.zju.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author godfu
 * @Date:2022/3/16-16:04
 */
public class BookServiceTest {
    BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "你为什么会单身", "didi", new BigDecimal(200), 200, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "你为什么会单身", "ooxx", new BigDecimal(200), 200, 100, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        List<Book> bookList = bookService.queryBooks();
        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }
}