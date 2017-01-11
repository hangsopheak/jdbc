# jdbc
java with jdbc connection (CRUD operation) using maven build
 
# Mysql sample with below Mysql database

create new database name : test
run the following sql in console
         
```java
       
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
         
    -- create procedure procedure
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
```

# Build project with maven + jetty server
>> mvn clean jetty:run

open browser : http://localhost:8080

//return as json content

http://localhost:8080/readDataServlet?type=json

