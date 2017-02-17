/**
 * 
 */
package com.rupp.sample.servlet;

import java.io.IOException;

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
public class DeleteDataServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DeleteDataServlet.class);
    private TestDaoImpl daoImpl = null;
    
    @Override
    public void init() throws ServletException {
        daoImpl = new TestDaoImpl();
    }
    /**
     * updateDataServlet
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.info("=====DeleteDataServlet.service method is called ====");

        String id = request.getParameter("id");
        
        try {
            //create new record
            if (id == null) {
                throw new ServletException("id can not be null");
            } else {
                daoImpl.deleteRecord(Integer.valueOf(id));
            }
            response.sendRedirect("/readDataServlet?message=<h1> Record has been deleted </h1>" );
            
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
