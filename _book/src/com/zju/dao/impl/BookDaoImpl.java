package com.zju.dao.impl;

import com.zju.dao.BaseDao;
import com.zju.dao.BookDao;
import com.zju.pojo.Book;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/16-14:09
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name`= ?, `author`= ?, `price`=?, `sales`=?, `stock`= ?, `img_path`= ? where id = ?";
        return update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book where id = ?";
        return queryForOne(sql, Book.class, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath from t_book";
        return queryForList(sql,Book.class);
    }
}
