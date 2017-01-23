package com.coviam.kafkaTrial.Services.Implementation;

import java.util.List;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coviam.kafkaTrial.DAO.UserDao;
import com.coviam.kafkaTrial.Model.User;
import com.coviam.kafkaTrial.Services.UserService;

@Service
public class UserServicesImplementation implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public void createUser(User s) {
		// TODO Auto-generated method stub
		userDao.save(s);
		
	}

	@Override
	public User getUser(int id) {
		// TODO Auto-generated method stub
		return userDao.findOne(id);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return Lists.newArrayList(userDao.findAll());
	}

	@Override
	public void updateUser(User s) {
		// TODO Auto-generated method stub
		userDao.delete(s.getUserID());
		userDao.save(s);  
	}	
}
