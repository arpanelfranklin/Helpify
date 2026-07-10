package com.example.helpify.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private String username;
    private String type; // campusTea, seniorGuidance, randomQuestion
    private String content;
    private ArrayList<String> comments;
    private int likes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime postedAt;
}
