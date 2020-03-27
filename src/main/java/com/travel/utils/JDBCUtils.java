package com.travel.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @auther Summerday
 */
public class JDBCUtils {

    //数据源
    private static DataSource ds;

    //加载配置文件
    static{
        try {
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            Properties prop = new Properties();
            prop.load(in);
            ds = DruidDataSourceFactory.createDataSource(prop);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取连接对象
     * @return 连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 获取数据源对象
     * @return 数据源
     */
    public static DataSource getDataSource(){
        return ds;
    }

}
