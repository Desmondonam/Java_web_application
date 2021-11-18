package com.example.restfulapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello World";
    }


    @GetMapping("/course")
    public courseBean courseBean(){
        return new courseBean("1", "Software Engineering");
    }


    @GetMapping("/courses/{course_id}")
    public String course(@PathVariable String course_id){
        return course_id;
    }




}
