package com.ConfigApp;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.GenericFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

import com.reponsitory.TokenReponsitory;

@Configuration
public class TokenConfig extends GenericFilterBean {
	
	private TokenReponsitory tokenReponsitory;
	
	TokenConfig(){
		this.tokenReponsitory = new TokenReponsitory();
	}
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String token = request.getHeader("Authorization");
        
        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
        	response.sendError(HttpServletResponse.SC_OK, "success");
        	return ;
        }
        
        
        if(allowRequestWithoutToken(request)) {
        	response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(req, res);
        }else {
        	if(token==null || !tokenReponsitory.isTokenValid(token))
        	{
        		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        	}
        	else
        	{
        		String userId = tokenReponsitory.getUserIdFromToken(token);
        		request.setAttribute("userId", userId);
        		filterChain.doFilter(req, res);
        	}
        }
        
	}
	
	public boolean allowRequestWithoutToken(HttpServletRequest request) 
	{ 
	    if (request.getRequestURI().contains("/user/login") || request.getRequestURI().contains("/post" )) {
	        return true;
	    }
	    return false;
	}

}
