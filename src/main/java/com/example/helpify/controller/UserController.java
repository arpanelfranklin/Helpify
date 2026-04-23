package com.example.helpify.controller;


import com.example.helpify.entity.User;
import com.example.helpify.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {

        User loggedInUser = userService.login(user.getEmail(), user.getPassword());

        session.setAttribute("user", loggedInUser);

        return "Login successful";
    }

    // ===== VERIFY OTP =====
    @PostMapping("/verify")
    public String verify(@RequestParam String email, @RequestParam String otp) {
        return userService.verifyOTP(email, otp);
    }

    // ===== CHECK SESSION =====
    @GetMapping("/me")
    public User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
    // ===== LOGOUT =====
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }
}
