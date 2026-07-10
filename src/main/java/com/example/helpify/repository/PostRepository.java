package com.example.helpify.repository;


import com.example.helpify.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUsername(String username);

    List<Post> findByType(String type);
}
