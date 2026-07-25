package com.jasmine.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jasmine.notification.model.Order;
import com.jasmine.notification.service.NotificationService;

@Component
public class OrderListener {
    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "order.notification.queue")
    public void receiveOrder(Order order) {
        try {
            System.out.println("Order baru diterima");
            System.out.println("ID: " + order.getId());
            System.out.println("Email: " + order.getEmail());
            System.out.println("Username: " + order.getUsername());

            notificationService.sendEmail(order);
        } catch (Exception e) {
            System.err.println("Gagal mengirim email: " + e.getMessage());
        }
    }
}