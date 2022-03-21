package com.zju.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author godfu
 * @Date:2022/3/10-16:15
 */
public class JdbcUtils {

    private static DruidDataSource dataSource;
    //引入ThreadLocal管理事务
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();

    //静态代码初始化读取资源，获取数据库连接池的操作
    static {
        try {
            Properties properties = new Properties();
            //读取jdbc属性文件到输入流
            InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("com/zju/resources/jdbc.properties");
            //从流中加载数据
            properties.load(is);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库的连接
     * @return 如果返回Null则说明连接失败，否则获取连接成功
     */
    public static Connection getConnection(){
        //从threadLocal中获取连接
        Connection conn = conns.get();
        //第一次获取连接
        if(conn == null){
            try {
                //从数据库连接池中获取连接
                conn = dataSource.getConnection();
                //保存到ThreadLocal对象中,供之后的jdbc使用
                conns.set(conn);
                //设置为手动事务管理
                conn.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try {
                //提交事务
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    //关闭事务
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        //一定要执行remove()操作，否则会报错，因为Tomcat底层使用了线程池技术
        conns.remove();
    }

    /**
     * 回滚事务，关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if (conn != null){
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
}
