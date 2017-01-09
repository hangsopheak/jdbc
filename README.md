# jdbc
java with jdbc connection
 
# Mysql sample with below database

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
         
```
