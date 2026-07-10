package com.example.helpify.service;

import com.example.helpify.entity.Post;
import com.example.helpify.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // get post
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    //create post
    public Post createPost(Post post){
        if(post.getContent() == null || post.getContent().isEmpty()){
            throw new RuntimeException("Empty post content");
        }
        post.setPostedAt(LocalDateTime.now());
        post.setLikes(0);
        return postRepository.save(post);
    }

    // get post by username
    public List<Post> getPostByUsername(String username){
        return postRepository.findByUsername(username);
    }

    //get post of campusTea
    public List<Post> getPostByType(String type){
         List<Post> typePosts = new ArrayList<>();
         List<Post> allPost = postRepository.findAll();
         for(Post post : allPost){
             if(post.getType().equals(type)){
                 typePosts.add(post);
             }
         }
         return typePosts;
    }

}
