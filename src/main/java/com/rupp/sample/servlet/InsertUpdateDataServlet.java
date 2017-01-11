/**
 * 
 */
package com.rupp.sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rupp.sample.jdbc.TestDaoImpl;

/**
 * @author sopheamak
 *
 */
public class InsertUpdateDataServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(InsertUpdateDataServlet.class);
    private TestDaoImpl daoImpl = null;
    
    @Override
    public void init() throws ServletException {
        daoImpl = new TestDaoImpl();
    }
    /**
     * updateDataServlet
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("=====InsertUpdateDataServlet.service method is called ====");
        //render to html page
       // Set response content type
        response.setContentType("text/html");
        
        String id = request.getParameter("id");
        String message = request.getParameter("message");
        
        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        
        try {
            //create new record
            if (id == null) {
                daoImpl.writeData(message);
                out.println(String.format("<h1> Record has been added </h1>"));
            } else {
                out.println(String.format("<h1> Record has been updated </h1>"));
                daoImpl.upateData(Integer.valueOf(id), message);
            }
            
            //get records from database talbe
            
            out.print("<a href='/readDataServlet'>See All</a>");
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("something is wrong with server " + e.getMessage());
        }
      
    }

    @Override
    public void destroy() {
        LOG.info("=====destroy method is called====");
    }

}
