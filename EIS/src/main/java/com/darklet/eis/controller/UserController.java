package com.darklet.eis.controller;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.darklet.eis.entity.User;
import com.darklet.eis.repository.UserRepository;
import com.darklet.eis.service.UserService;
import com.darklet.eis.util.MyBeanUtils;



@Controller
public class UserController {
@Autowired
UserService userService;
@Autowired
UserRepository userRepository;

//查询返回所有用户列表页面
@RequestMapping("/users")
public String UserList(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.ASC,page = 0)
                        Pageable pageable,Model model) {
	
	List<User> users = new ArrayList();
	users = userService.findAllUsers();
	//放在请求域中
	model.addAttribute("users",userService.listUsersofPage(pageable));
	//model.addAttribute("page",userService.listUsersofPage(pageable));
	//model.addAttribute("users",users);	//不带分页功能

	return "users/list";
	
}
//员工添加页面
@GetMapping("/user")
public String toAddUserPage(Model model) {
	return "users/add";	
}
//添加用户
@PostMapping("/user")
public String addUser(User user,Map<String,Object> map) {
     User result1 =  userService.checkUserName(user.getUsername());
     if(result1 != null) {
    	 //result.rejectValue("username","nameError","用户已经存在，请勿重复添加！");
    	 map.put("msg", "用户已经存在，请勿重复添加！");
     }else {
    		userService.addUser(user); 
     }

	return "redirect:/users";
}

//来到编辑页面，查出当前员工，在页面显示
@GetMapping("/user/{id}")
public String toEditPage(@PathVariable("id") Long id,Model model) {
	User user = userRepository.getOne(id);
	model.addAttribute("user",user);
	model.addAttribute("id",user.getId());
	return "users/edit";
}
//修改用户
@PutMapping("/user/{id}")
public String updateUser(@PathVariable("id") Long id,User user,HttpSession session) {

	user.setId(id);//写上这句话，id值就不会是null,save就会执行update语句，否则执行insert语句,JPA 新增和修改用的都是save. 它根据实体类的id是否为0来判断是进行增加还是修改
	//防止在update的时候把没有的字段变成NULL
	user.setLastmodifieduser(user.getUsername());
	Optional<User> optional = userRepository.findById(id);
	User origin = optional.get();
	MyBeanUtils.updateEntity(user, origin);
	
	//执行JPA默认的更新操作
     userRepository.save(origin);
	 return "redirect:/users";	  
	  }


//删除用户
@RequestMapping("/user/{id}")
public String deleteUser(@PathVariable("id") Long id) {
	//userService.deleteUserById(id);
	userService.deleteUserById(id);
	return "redirect:/users";
	
}


//退出登入
@GetMapping("/logout")
public String logout(HttpSession session) {
    session.removeAttribute("user");
    return "redirect:/index";
}



}
