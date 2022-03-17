package com.zju.service;

import com.zju.pojo.Book;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/16-15:31
 */
public interface BookService {
    /**
     * 添加图书
     * @param book
     */
    public void addBook(Book book);

    /**
     * 删除图书
     * @param id
     */
    public void deleteBookById(Integer id);

    /**
     * 更新图书信息
     * @param book
     */
    public void updateBook(Book book);

    /**
     * 根据ID查找图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 列表显示所有图书
     * @return
     */
    public List<Book> queryBooks();
}
