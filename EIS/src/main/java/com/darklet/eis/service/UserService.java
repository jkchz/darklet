package com.darklet.eis.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.darklet.eis.entity.User;
import com.darklet.eis.service.UserService;


public interface UserService  {

//Login页面判断用户名和密码
	 User checkUser(String username, String password);
	 
//查询所有用户	 
	 List<User> findAllUsers();
//分布显示所有用户
	 Page<User> listUsersofPage(Pageable pageable);
// 
	 User checkUserName(String username);
//添加用户
	 void addUser(User user);

//删除用户
	 @Query(value="update User set type='D' where id=?1",nativeQuery = true)
	 @Modifying
	 public void deleteUserById(Long id);
//修改用户
	 User updateUser(Long id, User user);

}
