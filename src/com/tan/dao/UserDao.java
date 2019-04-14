package com.tan.dao;

import com.tan.entity.User;

public interface UserDao {

	public User getUserByName(String userName);
	
	public int updateUser(User user);
	
	public int addUser(User user);
}
