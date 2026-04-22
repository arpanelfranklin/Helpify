package com.example.helpify.controller;

import com.example.helpify.entity.Order;
import com.example.helpify.entity.User;
import com.example.helpify.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAll() {
        return orderService.getAllOrders();
    }

    @PutMapping("/{id}/accept")
    public Order accept(@PathVariable String id, @RequestParam String user) {
        return orderService.acceptOrder(id, user);
    }

    @PutMapping("/{id}/complete")
    public Order complete(@PathVariable String id) {
        return orderService.completeOrder(id);
    }
    @GetMapping("/nearby")
    public List<Order> getNearby(HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }

        return orderService.getNearbyOrders(user);
    }
}

