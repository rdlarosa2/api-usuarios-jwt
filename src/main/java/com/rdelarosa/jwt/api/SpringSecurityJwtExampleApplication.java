package com.rdelarosa.jwt.api;

import com.rdelarosa.jwt.api.entity.Phone;
import com.rdelarosa.jwt.api.entity.User;
import com.rdelarosa.jwt.api.repository.UserDAO;
//import com.rdelarosa.jwt.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringSecurityJwtExampleApplication {
    //@Autowired
    //private UserRepository repository;

    @Autowired
    private UserDAO userDAO;

    @PostConstruct
    public void initUsers() {
        Date now = new Date();
        List<User> users = Stream.of(
                new User(null, "John Doe","user4@gmail.com", "password", "user4@gmail.com", now,null,now,null,true),
                new User(null,"Charles Johnson","user1@gmail.com", "pwd1", "user1@gmail.com", now,null,now,null,true),
                new User(null,"Donald Trump","user2@gmail.com", "pwd2", "user2@gmail.com", now,null,now,null,true),
                new User(null,"Joe Biden","user3@gmail.com", "pwd3", "user3@gmail.com", now,null,now,null,true)
        ).collect(Collectors.toList());
        userDAO.saveAll(users);

        userDAO.savePhone(new Phone(1, "1234567", "1", "57"));
        userDAO.savePhone(new Phone(1, "1234568", "1", "57"));

        userDAO.savePhone(new Phone(2, "1234569", "1", "57"));
        userDAO.savePhone(new Phone(2, "1234570", "1", "57"));

        userDAO.savePhone(new Phone(3, "1234571", "1", "57"));
        userDAO.savePhone(new Phone(3, "1234572", "1", "57"));

        userDAO.savePhone(new Phone(4, "1234573", "1", "57"));
        userDAO.savePhone(new Phone(4, "1234574", "1", "57"));

    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtExampleApplication.class, args);
    }

}
