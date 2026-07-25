package com.jasmine.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    private EmailService emailService; // 🔥 inject email service

    @RabbitListener(queues = "myQueue")
public void receiveMessage(Order order) {

    System.out.println("Pesan diterima: " + order.getNama());

    emailService.sendEmail(order);
}
}