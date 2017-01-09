package com.rupp.sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Allows an external client to make a request to this application to verify 
 * the application is alive.
 * 
 */
@SuppressWarnings("serial")
public class MonitorServlet extends HttpServlet {
	
	private static String version = null;
	
	static {
    	String propsFile = "application.properties";
    	Properties props = new Properties();
    	try {
			props.load(Thread.currentThread().getContextClassLoader().getResource(propsFile).openStream());
			version = "Version=" + props.getProperty("application.version");
		} catch (IOException e) {
			version = "Version=unknown";
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		resp.setContentType("text/plain");
		resp.setStatus(HttpServletResponse.SC_OK);
		PrintWriter writer = resp.getWriter();
		writer.println(version);
		writer.close();
	}
}
