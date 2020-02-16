package com.darklet.eis.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
/*@IdClass(User.class) //@IdClass来设置多个主键*/ 
public class User implements Serializable{

public User(Long id, String username, String password, Date createDate, Date updateTime, String type,
			String creatuser, String lastmodifieduser) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createDate = createDate;
		this.updateTime = updateTime;
		this.type = type;
		this.creatuser = creatuser;
		this.lastmodifieduser = lastmodifieduser;
	}


private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private Long id;

@Column(nullable = false) // 映射为字段，值不能为空
private String username;

@Column(nullable = false) // 映射为字段，值不能为空
private String password;

@CreatedDate
@Column
private Date createDate;

@LastModifiedDate
@Column
private Date updateTime;

@Column
private String type;
@CreatedBy
private String creatuser;
@LastModifiedBy
private String lastmodifieduser;


public User() {}



public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public Date getCreateDate() {
	return createDate;
}

public void setCreateDate(Date createDate) {
	this.createDate = createDate;
}

public Date getUpdateTime() {
	return updateTime;
}

public void setUpdateTime(Date updateTime) {
	this.updateTime = updateTime;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getCreatuser() {
	return creatuser;
}

public void setCreatuser(String creatuser) {
	this.creatuser = creatuser;
}

public String getLastmodifieduser() {
	return lastmodifieduser;
}

public void setLastmodifieduser(String lastmodifieduser) {
	this.lastmodifieduser = lastmodifieduser;
}

public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
}
