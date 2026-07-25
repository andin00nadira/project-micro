package com.jasmine.order.vo;

import lombok.Data;

@Data
public class Produk {
    public long id;
    private String nama;
    private String satuan;
    private String harga;
}