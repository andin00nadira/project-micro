package com.jasmine.order.vo;
import com.jasmine.order.model.Order;

import lombok.Data;

@Data
public class ResponseTemplate {
    Order order;
    Produk produk;
}