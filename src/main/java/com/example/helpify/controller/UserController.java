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

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpSession session) {
        User loggedInUser = userService.login(user.getUsername(), user.getPassword());

        session.setAttribute("user", loggedInUser);

        return "Login successful";
    }

    // CHECK SESSION
    @GetMapping("/me")
    public User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out";
    }
    @PostMapping("/location")
    public User updateLocation(@RequestBody Map<String, Double> loc, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            throw new RuntimeException("User not logged in");
        }

        user.setLatitude(loc.get("lat"));
        user.setLongitude(loc.get("lng"));

        return userService.save(user);
    }
}
