package com.darklet.eis.LocaleResolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
 /**
  * SpringBoot默认的Locale解析器是根据请求头的区域信息进行解析的（浏览器语言）
  * 使用自定义的Locale解析器对url的区域信息进行解析达到点击切换区域效果
  * 一旦我们自定义的区域解析器注册到Spring容器中，则SpringBoot提供的将不自动注册
  */
public class MyLocaleResolver implements LocaleResolver{

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String l =request.getParameter("lang");//lang对应的是login.html页面中的lang='zh_CN'和lang='en_US'
		Locale locale = Locale.getDefault();
		if(!StringUtils.isEmpty(l)) {
			String[] split = l.split("_");
			locale= new Locale(split[0],split[1]);
		}
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		// TODO Auto-generated method stub
		
	}

}
