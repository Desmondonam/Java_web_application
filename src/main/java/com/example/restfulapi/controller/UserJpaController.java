package com.example.restfulapi.controller;

import com.example.restfulapi.domain.Post;
import com.example.restfulapi.domain.User;
import com.example.restfulapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jpa")
public class UserJpaController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/uesrs/{id}/post")
    public List<Post> getPostsByUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        return user.get().getPosts();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> creatUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        return new ResponseEntity(user, HttpStatus.OK);

    }
}
