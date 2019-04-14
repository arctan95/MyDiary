package com.tan.service;

import com.tan.entity.User;

public interface UserService {

	public User checkLogin(String userName,String password) throws Exception;

	public int updateUser(User user);
	
	public int addUser(User user);
}
