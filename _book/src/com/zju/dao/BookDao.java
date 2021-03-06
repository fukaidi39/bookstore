package com.zju.dao;

import com.zju.pojo.Book;

import java.util.List;

/**
 * @author godfu
 * @Date:2022/3/16-11:02
 */
public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return 返回是-1时添加失败
     */
    public int addBook(Book book);

    /**
     * 根据ID删除图书
     * @param id
     * @return 返回-1时删除失败
     */
    public int deleteBookById(Integer id);

    /**
     * 更新图书信息
     * @param book
     * @return 返回-1时更新失败
     */
    public int updateBook(Book book);

    /**
     * 根据ID查询单项图书
     * @param id
     * @return
     */
    public Book queryBookById(Integer id);

    /**
     * 列表展示所有图书
     * @return
     */
    public List<Book> queryBooks();

    /**
     * 查询总记录数
     * @return
     */
    public Integer queryForPageTotalCount();

    /**
     * 查询分页显示的图书集合
     * @param begin
     * @param pageSize
     * @return
     */
    public List<Book> queryForPageItems(int begin, int pageSize);

    /**
     * 按价格区间查询记录数
     * @param min
     * @param max
     * @return
     */
    public Integer queryForPageTotalCountByPrice(int min, int max);

    /**
     * 根据价格区间查询分页显示图书集合
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
