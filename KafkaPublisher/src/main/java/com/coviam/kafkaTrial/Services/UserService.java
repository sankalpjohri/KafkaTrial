package com.coviam.kafkaTrial.Services;

import java.util.List;

import com.coviam.kafkaTrial.Model.User;

public interface UserService{
	void createUser(User s);
	User getUser(int id);
	List<User> getAllUsers();
	void updateUser(User s);
}
