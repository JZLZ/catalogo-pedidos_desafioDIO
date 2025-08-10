package com.jzlz.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/pedidos")
    public String pedidos() {
        return "Order Service online! 🟢";
    }
}
