package com.tan.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tan.dao.UserDao;
import com.tan.entity.User;
import com.tan.service.UserService;
import com.tan.util.MD5Util;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User checkLogin(String userName,String password) throws Exception{
		User user=userDao.getUserByName(userName);
		if(user!=null&&user.getPassword().equals(MD5Util.EncodePwdByMd5(password))){
			return user;
		}else{
			return null;
		}
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

}
