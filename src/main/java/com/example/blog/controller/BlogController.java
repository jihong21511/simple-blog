package com.example.blog.controller;


import com.example.blog.vo.Member;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {
    @RequestMapping(value="/sayHello", method = RequestMethod.GET)
    public String sayHello(){
        return "Hello, JIhong.";
    }

    @RequestMapping(value="/getInfo", method = RequestMethod.GET)
    public Member getInfo(){
        Member member = new Member(1,"jihong","jihong@naver.com");
/*        member.setId(1);
        member.setName("jihong");
        member.setEmail("jihong.com");*/
        return member;
    }

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<Member> list(){
        List<Member> memberList = Arrays.asList(
                new Member[]{new Member(1,"jihong1","jihong1@naver.com"),
                        new Member(2,"jihong1","jihong1@naver.com"),
                        new Member(3,"jihong1","jihong1@naver.com"),
                        new Member(4,"jihong1","jihong1@naver.com"),
                });

        long startTime = System.currentTimeMillis();
        for (Member member : memberList) {
            System.out.println(member);
        }
        System.out.println("for - loop:" + (System.currentTimeMillis() - startTime) + " ms");

        startTime = System.currentTimeMillis();
        memberList.forEach(System.out::println);
        System.out.println("lamda:" + (System.currentTimeMillis() - startTime) + " ms");



        return memberList;
    }
}
