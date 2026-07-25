package jasmineorder.Order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jasmineorder.Order.model.Order;
import jasmineorder.Order.repository.OrderRepository;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // 🔥 TAMBAHAN
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        // hitung total dulu
        order.hitungTotal();

        // simpan ke database
        Order saved = orderRepository.save(order);

        // 🔥 kirim ke RabbitMQ
        rabbitTemplate.convertAndSend("myQueue", saved.toString());

        System.out.println("Order dikirim ke RabbitMQ: " + saved);

        return saved;
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}