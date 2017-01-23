package com.coviam.kafkaTrial.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coviam.kafkaTrial.Model.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>{
	
}
