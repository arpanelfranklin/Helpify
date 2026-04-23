package com.example.helpify.service;

import com.example.helpify.entity.User;
import com.example.helpify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    // ===== REGISTER =====
    public User register(User user) {

        if (!user.getEmail().endsWith("@bennett.edu.in")) {
            throw new RuntimeException("Only college email allowed");
        }

        user.setVerified(false);

        String otp = generateOTP();
        user.setOtp(otp);

        sendEmail(user.getEmail(), otp);

        return userRepository.save(user);
    }
    // ===== LOGIN =====
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isVerified()) {
            throw new RuntimeException("Email not verified");
        }

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }
    // ===== VERIFY OTP =====
    public String verifyOTP(String email, String otp) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getOtp().equals(otp)) {
            user.setVerified(true);
            user.setOtp(null);
            userRepository.save(user);
            return "Verified successfully";
        }

        return "Invalid OTP";
    }
    // ===== GENERATE OTP =====
    public String generateOTP() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }

    // ===== SEND EMAIL =====
    public void sendEmail(String to, String otp) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setSubject("Verify your account");
        msg.setText("Your OTP is: " + otp);

        mailSender.send(msg);
    }
    // ===== SAVE USER (for location updates etc.) =====
    public User save(User user) {
        return userRepository.save(user);
    }

}
