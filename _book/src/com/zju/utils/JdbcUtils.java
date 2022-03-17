package com.zju.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

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
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接，将资源放回数据库连接池
     */
    public static void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
