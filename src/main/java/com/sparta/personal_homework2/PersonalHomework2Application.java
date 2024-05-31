package com.sparta.personal_homework2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableJpaAuditing
public class PersonalHomework2Application {

    public static void main(String[] args) {
        SpringApplication.run(PersonalHomework2Application.class, args);
    }

}
