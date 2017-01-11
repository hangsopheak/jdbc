/**
 * 
 */
package com.rupp.sample.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rupp.sample.domain.TestDomain;

/**
 * @author sopheamak
 *
 */
public class TestDaoImpl {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
 
    public TestDaoImpl() {
        
    }
    
    //delete sql
    public void deleteRecord(int id) throws SQLException {
        try {
            // get Connection from datasource
            connection = DBCP2DataSourceUtils.getConnection();

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
    //Insert SQL
    public void writeData(String message) throws Exception {
        try {
            connection = DBCP2DataSourceUtils.getConnection();
            // insert sql using prepared statement
            preparedStatement = connection.prepareStatement("insert into test_table(message) values (?)");
            // set parameter
            preparedStatement.setString(1, message); //set message
            preparedStatement.executeUpdate();
            System.out.println("Insert record successfully -" + message);
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
            // connection
            connection = DBCP2DataSourceUtils.getConnection();
            String sql = "update test_table set message=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            // set param message
            preparedStatement.setString(1, message);
            // set param id
            preparedStatement.setInt(2, id);
            boolean result = preparedStatement.execute();
            System.out.println("update record : " + result);
        }
        finally {
            close();
        }
    }
    // SQL Select
    public TestDomain readDataById(Integer id) throws Exception {
        if (id == null) {
            return null;
        }
        TestDomain result = null;
        try {
            // get Connection from datasource
            connection = DBCP2DataSourceUtils.getConnection();
            // execute select statement
            preparedStatement = connection.prepareStatement("select * from test_table where id=?");
            preparedStatement.setInt(1, id);
            
            resultSet = preparedStatement.executeQuery();
            
            // get result
            if (resultSet.next()) {
                String message = resultSet.getString("message");
                //System.out.println("id:" + id + ", message:" + message);
                //add TestDomain to List
                result = new TestDomain(id, message);
            }
            //DBCP2DataSourceUtils.printDataSourceState();
        } 
        finally {
            close();
        }
        return result;
    }
    
    // SQL Select
    public List<TestDomain> readData() throws Exception {
        final List<TestDomain> list = new ArrayList<>();
        try {
            // get Connection from datasource
            connection = DBCP2DataSourceUtils.getConnection();
            // create statement
            statement = connection.createStatement();
            // execute select statement
            resultSet = statement.executeQuery("select * from test_table");

            // get result
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String message = resultSet.getString("message");
                //System.out.println("id:" + id + ", message:" + message);
                //add TestDomain to List
                list.add(new TestDomain(id, message));
            }
            //DBCP2DataSourceUtils.printDataSourceState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
        return list;
    }
 
    private void close(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
