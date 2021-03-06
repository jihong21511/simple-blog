package com.example.blog.service;

import com.example.blog.vo.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserService {
    private static List<User> users = new ArrayList<User>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "jihong", new Date(),"jihong@naver.com"));
        users.add(new User(2, "boseock", new Date(),"boseock@naver.com"));
        users.add(new User(3, "handsome", new Date(),"handsome@naver.com"));
    }

    public List<User> findAll() {
        return users;
    }

    //1. 저장 -> save
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
            user.setJoinDate(new Date());

        }
        users.add(user);

        return user;
    }

    //2. 검색 -> findOne
    public User findOne(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    //3. 삭제 -> delete
    public User delete(int id) {
        // list -> search -> remove
/*        for(int i = 0; i< users.size(); i++){
            User user = users.get(i);

            if (user.getId() ==id);{
                users.remove(i);
            }

        }*/

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
/*        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return user;
            }
        }*/
        return null;
    }
    //4. 수정 -> modify
    public User modifiedUser(User paramUser) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User dbuser = iterator.next();
            if (dbuser.getId() == paramUser.getId()) {
                // update user_table set name=? where id=? -> list update
                // ...
                dbuser.setName(paramUser.getName());
                dbuser.setEmail(paramUser.getEmail());
                return dbuser;
            }
        }
        return null;
    }
}


