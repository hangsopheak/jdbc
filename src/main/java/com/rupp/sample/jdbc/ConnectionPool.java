package com.rupp.sample.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class ConnectionPool {
    private static DataSource ds = null;
    static {
        String propsFile = "db.properties";
        Properties props = new Properties();
        
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResource(propsFile).openStream());
            ds = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            props = null;
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void main(String[] args) throws Exception {
        try {
            // connection
            Connection con = ConnectionPool.getConnection();
            // statement
            Statement statement = con.createStatement();
            // execute select statement
            ResultSet resultSet = statement.executeQuery("select * from test_table;");
            // get result
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String message = resultSet.getString("message");
                System.out.println("id: " + id + "," + "message: " + message);
            }
            resultSet.close();
            statement.close();
            con.close();
        }
        finally {
            
        }
    }
}
