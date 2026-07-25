package com.jasmine.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

   public void sendEmail(Order order) {

    try {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(order.getEmail()); // 🔥 DINAMIS
        message.setSubject("Order Baru");

        message.setText(
            "Nama: " + order.getNama() +
            "\nMenu: " + order.getMenu() +
            "\nJumlah: " + order.getJumlah()
        );

        mailSender.send(message);

        System.out.println("✅ Email berhasil dikirim ke: " + order.getEmail());

    } catch (Exception e) {
        System.out.println("❌ Gagal kirim email");
        e.printStackTrace();
    }
}
}