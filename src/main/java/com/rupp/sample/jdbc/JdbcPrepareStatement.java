/**
 * 
 */
package com.rupp.sample.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author sopheamak
 *
 */
public class JdbcPrepareStatement {

    private final String jdbcDriverStr ="com.mysql.jdbc.Driver";
    private final String jdbcURL = "jdbc:mysql://localhost/test";
    private final String username = "root";
    private final String pwd = "root";
    
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private CallableStatement callableStatement;
 
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        /**
         * create new database name : test
         * run the following sql in console
         * 
         CREATE TABLE test_table (
             id INT NOT NULL AUTO_INCREMENT,
             message VARCHAR(400) NOT NULL,
             PRIMARY KEY (ID)
         ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 
         INSERT INTO test_table values (default, 'test text message');
         */
        JdbcPrepareStatement jdbc = new JdbcPrepareStatement();
       // jdbc.readData();
        
        //jdbc.writeData("my test message");
        
        //readMessageProcedure
        String message = jdbc.readMessageProcedure(2);
        System.out.println("Message with id 2 is " + message);
    }


    public void deleteRecord(int id) throws Exception {
        try {
            // load jdbc driver
            Class.forName(jdbcDriverStr);
            // load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);

            // insert sql using prepared statement
            preparedStatement = connection.prepareStatement("delete from test_table where id=?");
            // set parameter
            preparedStatement.setInt(1, id); //set id
            preparedStatement.executeUpdate();
        }
        finally {
            close();
        }
    }
    
    public void writeData(String message) throws Exception {
        try {
            // load jdbc driver
            Class.forName(jdbcDriverStr);
            // load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);

            // insert sql using prepared statement
            preparedStatement = connection.prepareStatement("insert into test_table values (default, ?)");
            // set parameter
            preparedStatement.setString(1, message); //set message
            preparedStatement.executeUpdate();
           
        }
        finally {
            close();
        }
    }
    /**
     
     //mysql database type procedure
    DELIMITER $$
    
    DROP PROCEDURE IF EXISTS `getMessage` $$
    CREATE PROCEDURE `getMessage` 
       (IN MY_ID INT, OUT MY_MESSAGE VARCHAR(255))
    BEGIN
       SELECT message INTO MY_MESSAGE
       FROM test_table
       WHERE id = MY_ID;
    END $$
    DELIMITER ;

     * @param id
     * @return
     * @throws Exception
     */
    public String readMessageProcedure(int id) throws Exception {
        String message = null;
        try {
            // load jdbc driver
            Class.forName(jdbcDriverStr);
            // load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);
            String sql = "{call getMessage (?, ?)}";
            // create call procedure
            callableStatement = connection.prepareCall(sql);
           // int id = 102;
            callableStatement.setInt(1, id); // This would set ID as 102
            // Because second parameter is OUT so register it
            callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
            // Use execute method to run stored procedure.
            callableStatement.execute();
            // Retrieve message with getXXX method
            message = callableStatement.getString(2);
           // System.out.println("Message with ID:" + id + " is " + message);
        }
        finally {
            close();
        }
        return message;
    }
    public void readData() throws Exception {
        try {
            //load jdbc driver
            Class.forName(jdbcDriverStr);
            //load connection driver
            connection = DriverManager.getConnection(jdbcURL, username, pwd);
            //create statement
            statement = connection.createStatement();
            //execute select statement
            resultSet = statement.executeQuery("select * from test_table;");
            //get result
            getResultSet(resultSet);
            
        } finally{
            close();
        }
    }
 
    private void getResultSet(ResultSet resultSet) throws Exception {
        while(resultSet.next()){
            Integer id = resultSet.getInt("id");
            String message = resultSet.getString("message");
            System.out.println("id: "+ id);
            System.out.println("message: "+ message);
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
            if (callableStatement != null) {
                callableStatement.close();
            }
        } catch(Exception e){}
    }
}
