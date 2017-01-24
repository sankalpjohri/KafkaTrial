package com.coviam.kafkaTrial.Model;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

import com.coviam.kafkaTrial.dao.OtpDao;

public class Subscriber {
    private CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "Registration")
    public void receiveMessage(String message) {
        System.out.println("received message = " + message);
        latch.countDown();
        OtpDao.getInstance().setValue(message, Integer.toString(new Random().nextInt(99999)));
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
