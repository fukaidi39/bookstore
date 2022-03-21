package com.zju.dao;

import com.zju.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * @author godfu
 * @Date:2022/3/10-18:59
 */
public abstract class BaseDao {
    //Dao层中不能关闭连接,事务结束后统一关闭;不能捕获异常，要抛出统一回滚事务
    //使用Dbutils操作数据库crud

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 执行增删改操作
     * @param sql:执行的sql语句
     * @param args:传入占位符中的参数
     * @return 返回-1表示执行失败，其他表示影响的函数
     */
    public int update(String sql, Object... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个javaBean对象的sql语句，根据查询属性调用set自动非javaBean赋值
     * @param sql:执行的sql语句
     * @param type:返回的对象类型
     * @param args:sql语句对应的参数
     * @param <T>：返回类型的泛型
     * @return
     */
    public <T> T queryForOne(String sql, Class<T> type, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new BeanHandler<T>(type), args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个javaBean对象的sql语句,根据查询属性调用set自动非javaBean赋值
     * @param sql:执行的sql语句
     * @param type:返回对象的类型
     * @param args: sql语句对应的参数
     * @param <T>:返回类型的泛型
     * @return
     */
    public <T>List<T> queryForList(String sql, Class<T> type, Object ... args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type),args);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回单个值的对象(一行一列)
     * @param sql:执行的sql语句
     * @param args:sql语句对应的参数
     * @return：值
     */
    public Object queryForSingleValue(String sql, Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn,sql, new ScalarHandler(),args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
