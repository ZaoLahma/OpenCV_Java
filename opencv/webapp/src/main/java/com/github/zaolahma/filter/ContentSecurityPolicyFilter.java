package com.github.zaolahma.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ContentSecurityPolicyFilter implements Filter {
	String mCSPHeaderContents = "";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		mCSPHeaderContents = filterConfig.getInitParameter("content-security-policy");
		System.out.println("CSP: " + mCSPHeaderContents);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (null != mCSPHeaderContents && !mCSPHeaderContents.isEmpty()) {
			HttpServletResponse httpServletResponse = ((HttpServletResponse) response);
		    httpServletResponse.addHeader("Content-Security-Policy", mCSPHeaderContents);
		    System.out.println("Added headers: " + mCSPHeaderContents);
		}
		
	    chain.doFilter(request, response);
	}

}
