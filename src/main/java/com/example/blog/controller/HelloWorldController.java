package com.example.blog.controller;

import com.example.blog.vo.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @RequestMapping(value="/hello-world", method = RequestMethod.GET)
    public String helloWorld() {
        return "hello-world";
    }

    @RequestMapping(value="/hello-world-bean", method = RequestMethod.GET)
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world Bean");
    }

    @RequestMapping(value="/hello-world/path-variable/{name}", method = RequestMethod.GET)
    public HelloWorldBean helloWorldBeanPathVariable(
            @PathVariable("name") String name
            ){
            return new HelloWorldBean(String.format("Hello world, %s", name));
            }
        }



