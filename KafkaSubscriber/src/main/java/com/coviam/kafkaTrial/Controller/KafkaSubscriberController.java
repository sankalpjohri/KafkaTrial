package com.coviam.kafkaTrial.Controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coviam.kafkaTrial.Model.Subscriber;

@RestController
public class KafkaSubscriberController {
	
	@Autowired
	Subscriber subscriber;
	
	@RequestMapping("/generateOtp")
	public String generateOTP(){
		try {
			subscriber.getLatch().await(10000, TimeUnit.MILLISECONDS);
			return "OTP Generated";
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "OTP Generation Failed";
	}
		
}
