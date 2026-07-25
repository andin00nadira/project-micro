package com.jasmine.order.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.jasmine.order.model.Order;
import com.jasmine.order.repository.OrderRepository;
import com.jasmine.order.vo.Produk;
import com.jasmine.order.vo.ResponseTemplate;


import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Order createOrder(Order order, String username, String email, String role) {
        order.setTanggal(LocalDateTime.now().format(formatter));
        order.setUsername(username);
        order.setEmail(email);
        order.setRole(role);

        Order savedOrder = orderRepository.save(order);

        System.out.println("ID dikirim: " + savedOrder.getId());
        System.out.println("Username dikirim: " + savedOrder.getUsername());
        System.out.println("Role: " + savedOrder.getRole());

        rabbitTemplate.convertAndSend(
                "",
                "order.notification.queue",
                savedOrder);

        return savedOrder;
    }

    @Transactional
    public void update(Long id, Integer jumlah, String tanggal, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalStateException("Order tidak ada"));
        if (jumlah != null) {
            order.setJumlah(jumlah);
        }
        if (tanggal != null && tanggal.length() > 0 && !Objects.equals(order.getTanggal(), tanggal)) {
            order.setTanggal(tanggal);
        }
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id) {
        List<ResponseTemplate> responseList = new ArrayList<>();
        Order order = getOrderById(id);
        ServiceInstance serviceInstance = discoveryClient.getInstances("PRODUK").get(0);
        Produk produk = restTemplate.getForObject(serviceInstance.getUri() + "/api/produk/" + order.getId_produk(),
                Produk.class);
        ResponseTemplate vo = new ResponseTemplate();
        vo.setOrder(order);
        vo.setProduk(produk);
        responseList.add(vo);
        return responseList;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
