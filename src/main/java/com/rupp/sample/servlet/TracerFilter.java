package com.rupp.sample.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * @author Sophea <a href='mailto:smak@dminc.com'> sophea </a>
 * @version $id$ - $Revision$
 * @date 2017
 */
public class TracerFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
		throws IOException, ServletException {
		Tracer.create();
		Tracer.mark("start:http:" + ((HttpServletRequest) request).getRequestURI());
		try {
			chain.doFilter(request, response);
		} finally {
			Tracer.markAndOutput("stop:http");
			Tracer.destroy();
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
