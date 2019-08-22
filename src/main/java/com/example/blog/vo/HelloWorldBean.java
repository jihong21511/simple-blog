package com.example.blog.vo;
import lombok.Data;

//@Data lombok 쓰면 셋겟 자동으로 만들어줌
@Data
public class HelloWorldBean {
    private String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean{message='%s'", message);
/*        StringBuilder sb = new StringBuilder();
        sb.append("HelloWorldBean{");
        sb.append("message='")
                .append("message")
                .append('\'')
                .append('}');


        return sb.toString();*/
    }
}
