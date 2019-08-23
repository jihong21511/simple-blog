package com.example.blog.controller;

import com.example.blog.vo.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value="/hello-world", method = RequestMethod.GET)
    public String helloWorld(@RequestParam(required = false) String name,@RequestParam(required = false) String email) {
        if (name == null){
            name = "world";
        }
        if (email == null) {
            email = "test@example.com";
        }
        return messageSource.getMessage("hello.message",new Object[]{name,email}, LocaleContextHolder.getLocale());
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

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        String msg = messageSource.getMessage("good.morning.message", null,
                LocaleContextHolder.getLocale());
        return msg;
    }
        }



