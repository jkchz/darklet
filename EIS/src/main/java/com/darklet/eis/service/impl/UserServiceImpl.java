package com.darklet.eis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.darklet.eis.entity.User;
import com.darklet.eis.repository.UserRepository;
import com.darklet.eis.service.UserService;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
    UserRepository userRepository;////与Repository(Dao)层进行交互

	@Override
	public User checkUser(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		return user;
	}
//查询返回所有用户
	@Override
	public List<User> findAllUsers() {
		List<User> userlist = new ArrayList();
		userlist = (List<User>) userRepository.findAll();
		return userlist;
	}
	//添加用户
	@Override
	public void addUser(User user) {
		userRepository.save(user);
		
	}



	@Override
	@Transactional
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
		
	}
	
	@Transactional
	@Override
	public User updateUser(Long id, User user) {
		return null;
		//User t = userRepository.findById(id).get();
        //BeanUtils.copyProperties(user, t);
		//return userRepository.save(user);

	}
	@Override
	public Page<User> listUsersofPage(Pageable pageable) {
		
		return userRepository.findAll(pageable);
	}
	@Override
	public User checkUserName(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}





}
