package com.coviam.kafkaTrial.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coviam.kafkaTrial.DAO.OtpDao;
import com.coviam.kafkaTrial.Model.KafkaProducer;
import com.coviam.kafkaTrial.Model.User;
import com.coviam.kafkaTrial.Services.UserService;

@RestController
public class PublisherController {
	
	@Autowired
	UserService userService;
	
	@Autowired
    private KafkaProducer producer;
	
	@RequestMapping("/")
	public String testFunction(){
		return "Hello World";
	}
	
	@RequestMapping("/Register")
	public String registerRequest(@RequestParam("email") String userEmail, @RequestParam("password") String userPassword, 
			@RequestParam("phone") String userPhone){	
		int userID = 0;
		User tempUser = new User(userEmail, userPassword, userPhone); 
 		userService.createUser(tempUser);
 		List<User> userList = userService.getAllUsers();
 		for (int i = 0; i < userList.size(); i++){
 			if (userList.get(i).getUserEmail().equals(userEmail) && userList.get(i).getUserPassword().equals(userPassword)){
 				userID = userList.get(i).getUserID();
 				producer.sendMessage("Registration", Integer.toString(userID));
 			}
 		}
		return "OTP Sent to " + userPhone + " for userId = " + Integer.toString(userID);
	}
	
	@RequestMapping("/registerAuthentication")
	public String registerAuthentication(@RequestParam("user_id") String user_id, @RequestParam("otp") String otp){
		OtpDao getOtp = OtpDao.getInstance();
		if (getOtp.getValue(user_id).equals(otp)){
			getOtp.delValue(user_id);
			return "Registration Complete";
		} else {
			return "Registration Failed";
		}
	}
}
