package com.example.helpify.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;

    private boolean verified = false;

    private String otp; // for email verification

    private double latitude;
    private double longitude;
}
