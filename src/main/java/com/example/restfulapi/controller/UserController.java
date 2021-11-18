package com.example.restfulapi.controller;

import com.example.restfulapi.domain.User;
import com.example.restfulapi.service.UserService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {



    private UserService userService;

    public UserController(UserService userService ){
        this.userService = userService;

    }

    @GetMapping("/users")
    public List<User> Allusers(){
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id){
        User user = userService.findOne(id);
        if (user == null){
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }

        //HATEOAS
        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).Allusers());
        model.add(linkTo.withRel("all-users"));
        return model;

    }

    @PostMapping("/user")
    public ResponseEntity<User> creatUser(@RequestBody User user){
        User newUser = userService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userService.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%d] not found", id));
        }
    }


}
