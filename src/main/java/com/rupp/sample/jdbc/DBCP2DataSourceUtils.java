package com.rupp.sample.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DBCP2DataSourceUtils {
    private static BasicDataSource ds = null;
    
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
    /**get connection from pool*/
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    
    public static DataSource getDataSource() {
        return ds;
    }
    public static void printDataSourceState() {
        System.out.println("NumActive: " + ds.getNumActive());
        System.out.println("NumIdle: " + ds.getNumIdle());
    }
    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.setProperty("driverClassName", "com.mysql.jdbc.Driver");
        props.setProperty("url", "jdbc:mysql://localhost:3306/test?autoReconnect=true");
        props.setProperty("username", "root");
        props.setProperty("password", "root");
        // props.setProperty("maxWaitMillis", "6000");
        // props.setProperty("fastFailValidation", "1");
        // props.setProperty("validationQueryTimeout", "1");
        // props.setProperty("maxTotal", "500");
        // props.setProperty("maxIdle", "5");

        // set datasource
        BasicDataSource ds = BasicDataSourceFactory.createDataSource(props);

        Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // connection
            con = ds.getConnection();
            // statement
            statement = con.createStatement();
            // execute select statement
            resultSet = statement.executeQuery("select * from test_table;");
            // get result
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String message = resultSet.getString("message");
                System.out.println("id: " + id + "," + "message: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
