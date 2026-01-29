package com.example.spring_test_containers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class HelloController {


    @GetMapping("/hello/{username}")
    public String hello(@PathVariable("username") String username){
        return String.format("Hell %s!", username);
    }

}
