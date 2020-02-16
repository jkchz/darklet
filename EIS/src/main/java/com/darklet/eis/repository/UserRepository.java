package com.darklet.eis.repository;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.darklet.eis.entity.User;
import java.lang.String;


public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User>{
//CrudRepository<User, Long>{	
	User findByUsernameAndPassword(String username, String password);
  
//判断用户是否已经存在	 
    User findByUsername(String username);

}
