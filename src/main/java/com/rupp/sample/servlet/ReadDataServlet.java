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
import com.rupp.sample.jdbc.SampleSqlSelect;

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
        out.println(String.format("<h1> Records table result </h1>"));
        
        SampleSqlSelect sampleSqlSelect = new SampleSqlSelect();
        
        try {
            //get records from database talbe
            List<TestDomain> results = sampleSqlSelect.readDataFromDataSource();
            
            out.print("<table width='500px' border='1'>");
            out.print("<tr bgcolor='gray'><td>Id</td><td>message</td></tr>");
            for (TestDomain testDomain : results) {
                out.print(String.format("<tr><td>%s</td><td>%s</td></tr>", testDomain.getId(), testDomain.getMessage()));
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
