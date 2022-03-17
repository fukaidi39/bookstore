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

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        //Scalar这个方法的返回值是Object，运行类型是long,不能用Integer强转，用父类型Number接受
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath " +
                "from t_book limit ?,?";
        return queryForList(sql,Book.class,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        //Scalar这个方法的返回值是Object，运行类型是long,不能用Integer强转，用父类型Number接受
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`, `name`, `author`, `price`, `sales`, `stock`, `img_path` imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(sql,Book.class,min,max,begin,pageSize);
    }
}
