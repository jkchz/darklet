package com.darklet.eis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.darklet.eis.LocaleResolver.MyLocaleResolver;
import com.darklet.eis.interceptor.LoginHandlerInterceptor;


 /**
  * 扩展SpringMVC
  * SpringBoot2使用的Spring5，因此将WebMvcConfigurerAdapter改为WebMvcConfigurer
  * 使用WebMvcConfigurer扩展SpringMVC好处既保留了SpringBoot的自动配置，又能用到我们自己的配置
  */
 //@EnableWebMvc //如果我们需要全面接管SpringBoot中的SpringMVC配置则开启此注解，
                 //开启后，SpringMVC的自动配置将会失效

@Configuration
public class MyWebConfig implements WebMvcConfigurer{
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//设置对"/"的请求映射到index.html
		//registry.addViewController("/").setViewName("index");
		
		//登入成功映射
		registry.addViewController("/main.html").setViewName("dashboard");
	}
	
	
//注册拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		  //静态资源； *.css , *.js
		  //SpringBoot已经做好了静态资源映射 
			
			//注册自定义拦截器，添加拦截路径和排除拦截路径
		 registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/main.html","/users","/user/**")
		  .excludePathPatterns("/","/index.html","/user/login","/static/**","/templates/**","/asserts/**","/login","/login.html?lang=en_US");
		  
	}


	
	//注册我们的自定义区域解析器
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}

}
