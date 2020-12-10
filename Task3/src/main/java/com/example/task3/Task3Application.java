package com.example.task3;

import com.example.task3.domain.User;
import com.example.task3.domain.UserWithLombok;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootApplication
@SpringBootTest
public class Task3Application {
    private static Logger logger = Logger.getLogger(Task3Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Task3Application.class, args);
        User firstUser = new User("kate", "***", "****", "****", "****", LocalDate.now());
        UserWithLombok secondUser =
                new UserWithLombok("mary", "****", "****", "****", "****", LocalDate.now());
        if (logger.isDebugEnabled()) {
            logger.debug("First " + firstUser);
            logger.debug("Second " + secondUser);
        }
    }
}
