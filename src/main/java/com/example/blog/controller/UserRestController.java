package com.example.blog.controller;

import com.example.blog.exception.UserNotFoundException;
import com.example.blog.service.UserService;
import com.example.blog.vo.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class UserRestController {
    //UserService service = new UserService();
    //우리가 생성하면 안되 Bean 에서 만들어 놓은것을 선언만 해서 해야되. 스프링은 빈을 이용해서 관리 해주니까
    @Autowired
    UserService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable("id") int id) { // 1 -> "1" -> 1
        User user = service.findOne(id);
        if (user == null){
            throw new UserNotFoundException("id-" + id);
        }

        Resource<User> resource = new Resource<User>(user);
        //retrieveAllUsers
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    //save
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
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

    @PutMapping("/users/{id}")
    public User modifyUser(@PathVariable("id") int id,
                           @RequestBody User user) {
        user.setId(id);
        User modifiedUser = service.modifiedUser(user);
        if (modifiedUser == null) {
            throw new UserNotFoundException("삭제할 id가 없다");
        }
        return modifiedUser;
    }
}