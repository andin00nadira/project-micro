package com.jasmine.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasmine.order.model.Order;
import com.jasmine.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestParam;

import com.jasmine.order.vo.ResponseTemplate;

import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrder() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order,
            jakarta.servlet.http.HttpServletRequest request) {

        String username = (String) request.getAttribute("username");
        String email = (String) request.getAttribute("email");
        String role = (String) request.getAttribute("role");

        return orderService.createOrder(order, username, email, role);
    }

    @GetMapping(path = "/produk/{id}")
    public List<ResponseTemplate> getOrderWithProdukById(@PathVariable("id") long id) {
        return orderService.getOrderWithProdukById(id);
    }

    @PutMapping(path = "{id}")
    public void updateOrder(@PathVariable("id") long id,
            @RequestParam(required = false) int jumlah,
            @RequestParam(required = false) String tanggal,
            @RequestParam(required = false) String status) {
        orderService.update(id, jumlah, tanggal, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

}
