package com.coviam.kafkaTrial.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public class KafkaProducer {
	
	@Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;
	
	public void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate
                .send(topic, message);
        
        future.addCallback(
                new ListenableFutureCallback<SendResult<Integer, String>>() {

                    @Override
                    public void onSuccess(
                            SendResult<Integer, String> result) {
                    	System.out.println("Sent Message: " + message);
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                    	System.out.println("Unabe to send message");
                    }
                });
    }
}
