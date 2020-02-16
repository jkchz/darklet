package com.darklet.eis.config;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;


@Component
public class UserIDAuditorBean implements AuditorAware<String>{
    @Autowired
    private HttpServletRequest request;
     
    @Autowired
    private HttpServletResponse response;
    
	@Override
	public Optional<String> getCurrentAuditor() {
		String lastmodifieduser = null;
		//return Optional.of("admin")		
	     //ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	     //String lastmodifieduser=servletRequestAttributes.getRequest().getCookies().toString();
	     Cookie[] cookies =  request.getCookies();
	     if (cookies != null) {
	         for(Cookie cookie : cookies){
	             if(cookie.getName().equals("loginUser")){
	            	 lastmodifieduser =  cookie.getValue();
	             }
	         }
	     }

	     return Optional.of(lastmodifieduser);


	}

}
