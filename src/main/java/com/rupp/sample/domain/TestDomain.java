/***/
package com.rupp.sample.domain;

/**
 * map to table test_table (id, message)
 * @author Sophea <a href='mailto:smak@dminc.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2017
 */
public class TestDomain {
    private Integer id;
    private String message;
    public TestDomain() {
        
    }
    public TestDomain(Integer id, String message) {
        this.id = id;
        this.message = message;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String toString() {
        return String.format("id : %s, message : %s", id, message);
    }
    
}
