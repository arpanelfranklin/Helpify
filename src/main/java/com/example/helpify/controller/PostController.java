package com.example.helpify.controller;

import com.example.helpify.entity.Post;
import com.example.helpify.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }

    //create order
    @PostMapping
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    //get post by type
    @GetMapping("/{type}")
    public List<Post> getAllPostByType(@PathVariable String type){
        return postService.getPostByType(type);
    }
}
