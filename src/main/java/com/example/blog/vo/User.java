package com.example.blog.vo;

import lombok.Data;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class User {
    private Integer id;
    @Size(min=2, message="2글자 이상 입력해 주세요.")
    private String name;
    @Email(message="Email 양식으로 입력해 주세요.")
    private String email;
    private Date joinDate;

    public User(Integer id, String name, Date joinDate,String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return String.format("User [id=%s, name=%s, joinDate=%s, email=%s]",id,name,joinDate,email);
    }
}
