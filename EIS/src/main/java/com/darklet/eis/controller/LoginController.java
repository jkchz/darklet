package com.darklet.eis.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.darklet.eis.entity.User;
import com.darklet.eis.service.UserService;



@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;
     
    @Autowired
    private HttpServletResponse response;
    
	@RequestMapping({"/","index","/index.html","/login","/login.html","/user/login"})
	public String index() {
		return "login";
	}
	
	@PostMapping(value = "/user/login")
	//@RequestParam注解，就是用来接收前台传回来的数据，然后传到service层
	public String  login(@RequestParam(value="username") String username,
                         @RequestParam(value="password") String password,
                          HttpSession session,
                          Map<String, Object> map) {
		User user = userService.checkUser(username, password);
		 if(user != null) {
			 //user.setPassword(null);
			 session.setAttribute("user", user);	
			 session.setAttribute("loginUser", username);
				// new cookie 空间
			    Cookie cname = new Cookie("loginUser", username);
			    String user1 = cname.getValue();
			    response.addCookie(cname);
			    System.out.println(cname.getValue());
			// 登入成功，防止表单重复提交，可以通过重定向到主页
			 return "redirect:/main.html"; // main.html有视图映射到dashboard,在WebConfig类中
		 }else {
			 map.put("msg", "用户名和密码错误！");
			 return "login";
		 }
				
	}

}
