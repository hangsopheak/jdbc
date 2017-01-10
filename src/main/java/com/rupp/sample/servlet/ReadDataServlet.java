/**
 * 
 */
package com.rupp.sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rupp.sample.domain.TestDomain;
import com.rupp.sample.jdbc.test.SampleSqlSelect;

/**
 * @author sopheamak
 *
 */
public class ReadDataServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=====ReadDataServlet.service method is called ====");
        //render to html page
       // Set response content type
        response.setContentType("text/html");
        
        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        String message = request.getParameter("message");
        
        if (message !=null) {
            out.print(message);
        }
        out.println(String.format("<h1> Records table result </h1>"));
        
        SampleSqlSelect sampleSqlSelect = new SampleSqlSelect();
        
        try {
            //dbType is  1 is NATIVE-SQL  else is DBCP 
            String dbType = request.getParameter("dbType");
            
            //get records from database talbe
            //List<TestDomain> results = sampleSqlSelect.readDataFromDataSource();
            List<TestDomain> results = "1".equals(dbType) ? sampleSqlSelect.readData() : sampleSqlSelect.readDataFromDataSource();
            out.print("<a href='edit.jsp'> create new</a>");
            out.print("<table width='500px' border='1'>");
            out.print("<tr bgcolor='gray'><td>Id</td><td>message</td><td>delete</td></tr>");
            for (TestDomain testDomain : results) {
                out.print(String.format("<tr><td><a href='edit.jsp?id=%s'>%s</a></td>"
                        + "<td>%s</td>"
                        + "<td><a href='/deleteDataServlet?id=%s'>Delete</a>"
                        + "</td></tr>", testDomain.getId(),
                        testDomain.getId(), testDomain.getMessage(), testDomain.getId()));
            }
            out.print("</table>");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("something is wrong with server " + e.getMessage());
        }
      
    }

    @Override
    public void destroy() {
        System.out.println("=====destroy method is called====");
    }

}
