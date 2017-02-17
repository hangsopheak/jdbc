/**
 * 
 */
package com.rupp.sample.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rupp.sample.domain.TestDomain;
import com.rupp.sample.jdbc.DBCP2DataSourceUtils;

/**
 * @author sopheamak
 *
 */
public class TestDaoJdbcTemplate {

    private static JdbcTemplate jdbcTemplate = new JdbcTemplate(DBCP2DataSourceUtils.getDataSource());
    
    /**
     * @param args
     */
    public static void main(String[] args) {

        
        //count 
        int rowCount = jdbcTemplate.queryForObject("select count(*) from test_table", Integer.class);
        System.out.println("Records found :" + rowCount);
        
        //A simple query using a bind variable:
        rowCount = jdbcTemplate.queryForObject("select count(*) from test_table where message=?", Integer.class, "hello");
        System.out.println("Records found with filter:" + rowCount);
        
        //Querying for a String:
        
        String message = jdbcTemplate.queryForObject(
                "select message from test_table where id = ?",
                new Object[]{7L}, String.class);
        System.out.println("message is " + message);
        
        //Querying and populating a single domain object:
        TestDomain domain = jdbcTemplate.queryForObject(
                "select * from test_table where id = ?",
                new Object[]{6L},
                new RowMapper<TestDomain>() {
                    public TestDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
                        TestDomain actor = new TestDomain();
                        actor.setId(rs.getInt("id"));
                        actor.setMessage(rs.getString("message"));
                        return actor;
                    }
                });
        System.out.println("=======" + domain);
        
        //Querying and populating a number of domain objects as List:
        testReturnListObject();

    }
    //return as list
    public static void testReturnListObject() {
        final List<TestDomain> domains = jdbcTemplate.query("select * from test_table", new RowMapper<TestDomain>() {
            public TestDomain mapRow(ResultSet rs, int rowNum) throws SQLException {
                TestDomain testDomain = new TestDomain();
                testDomain.setId(rs.getInt("id"));
                testDomain.setMessage(rs.getString("message"));
                return testDomain;
            }
        });
        System.out.println("List of message");
        for(TestDomain testDomain : domains) {
            System.out.println(testDomain);
        }
    }
    
//    public List<Actor> findAllActors() {
//        return this.jdbcTemplate.query( "select first_name, last_name from t_actor", new ActorMapper());
//    }
//
//    private static final class ActorMapper implements RowMapper<Actor> {
//
//        public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Actor actor = new Actor();
//            actor.setFirstName(rs.getString("first_name"));
//            actor.setLastName(rs.getString("last_name"));
//            return actor;
//        }
//    }
    
    
/**
Updating (INSERT/UPDATE/DELETE) with jdbcTemplate

You use the update(..) method to perform insert, update and delete operations. 
Parameter values are usually provided as var args or alternatively as an object array.

this.jdbcTemplate.update(
        "insert into t_actor (first_name, last_name) values (?, ?)",
        "Leonor", "Watling");
this.jdbcTemplate.update(
        "update t_actor set last_name = ? where id = ?",
        "Banjo", 5276L);
this.jdbcTemplate.update(
        "delete from actor where id = ?",
        Long.valueOf(actorId));
 
 
 Other jdbcTemplate operations

You can use the execute(..) method to execute any arbitrary SQL, and as such the method is often used for DDL statements. 
It is heavily overloaded with variants taking callback interfaces, binding variable arrays, and so on.

this.jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
The following example invokes a simple stored procedure. More sophisticated stored procedure support is covered later.

this.jdbcTemplate.update(
        "call SUPPORT.REFRESH_ACTORS_SUMMARY(?)",
        Long.valueOf(unionId));
        
 *
 *
 */       
    

}
