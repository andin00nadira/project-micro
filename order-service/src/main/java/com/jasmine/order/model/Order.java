package com.jasmine.order.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long id_produk;
    private long id_pelanggan;
    private double harga;
    private int jumlah;
    private double total;
    private String email;
    private String tanggal;
    private String username;
    private String role;
}

