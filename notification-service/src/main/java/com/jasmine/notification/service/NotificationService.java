package com.jasmine.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.jasmine.notification.model.Order;

@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Order order) {
        if (order.getEmail() == null || order.getEmail().isBlank()) {
            System.out.println("Email tidak tersedia. Notifikasi dibatalkan.");
            return;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getEmail());
        message.setSubject("Order Berhasil");

        String isiEmail = "Username: "  + order.getUsername() + "\n" +
                "ID Order: " + order.getId() + "\n" +
                "ID Pelanggan: " + order.getId_pelanggan() + "\n" +
                "ID Produk: " + order.getId_produk() + "\n" +
                "Jumlah: " + order.getJumlah() + "\n" +
                "Total: " + order.getTotal() + "\n" +
                "Tanggal: " + order.getTanggal();

        message.setText(isiEmail);
        mailSender.send(message);
        System.out.println("Email berhasil dikirim ke: " + order.getEmail());
    }
}
