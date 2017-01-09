/**
 * 
 */
package com.rupp.sample.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sopheamak
 *
 */
public class SampleInsertUpdatDeleteStatement {

    private final String jdbcDriverStr ="com.mysql.jdbc.Driver";
    private final String jdbcURL = "jdbc:mysql://localhost/test";
    private final String username = "root";
    private final String pwd = "root";
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
 
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /**
         * create new database name : test
         * run the following sql in console
         * 
         DROP DATABASE IF EXISTS test;
         CREATE DATABASE test;
         USE test;
         
         DROP TABLE IF EXISTS test_table;
         CREATE TABLE test_table (
             id INT NOT NULL AUTO_INCREMENT,
             message VARCHAR(400) NOT NULL,
             PRIMARY KEY (ID)
         ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 
         INSERT INTO test_table values (default, 'test text message');
         */
        SampleInsertUpdatDeleteStatement jdbc = new SampleInsertUpdatDeleteStatement();
        
        //insert record
        jdbc.writeData("my test message");
        //update
        jdbc.upateData(2, "update my message");
        //delete
        jdbc.deleteRecord(2);
    }


    public void writeData(String message) throws Exception {
        try {
            // load jdbc driver
            Class.forName(jdbcDriverStr);
            // load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);
         // create statement
            statement = connection.createStatement();
            
            // insert sql using  statement
            statement.execute("insert into test_table(message) values ('"+ message +"')");
            
            System.out.println("Insert record successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }
    
    /***
     * update record table
     * @param id
     * @param message
     * @throws Exception
     */
    public void upateData(int id, String message) throws Exception {
        try {
            // load jdbc driver
            Class.forName(jdbcDriverStr);
            // load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);
         // create statement
            statement = connection.createStatement();
            
            // insert sql using  statement
            statement.execute("update test_table set message='"+ message +"' where id="+ id);
            System.out.println("update record successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }
 
    /***
     * update record table
     * @param id
     * @param message
     * @throws Exception
     */
    public void deleteRecord(int id) throws Exception {
        try {
            // load jdbc driver
            Class.forName(jdbcDriverStr);
            // load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);
         // create statement
            statement = connection.createStatement();
            
            // delete sql using  statement
            int record = statement.executeUpdate("delete from test_table where id="+ id);
            System.out.println("deleted : [" +record + "] record successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }
    private void close(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
