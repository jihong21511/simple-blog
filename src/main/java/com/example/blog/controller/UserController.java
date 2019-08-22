package com.example.blog.controller;

import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.UserService;
import com.example.blog.vo.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    //UserService service = new UserService();
    //우리가 생성하면 안되 Bean 에서 만들어 놓은것을 선언만 해서 해야되. 스프링은 빈을 이용해서 관리 해주니까
    @Autowired
    UserService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable("id") int id) { // 1 -> "1" -> 1
        return service.findOne(id);
    }

    //save
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User savedUser= service.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void removeUser(@PathVariable("id") int id) {
        User deleteresult = service.delete(id);
        if (deleteresult == null) {
            throw new UserNotFoundException("삭제할 id가 없다");
        }
    }
}