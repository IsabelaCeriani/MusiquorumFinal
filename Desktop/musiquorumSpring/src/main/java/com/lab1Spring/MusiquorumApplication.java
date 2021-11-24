package com.lab1Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.lab1Spring.musiquorum.repositories")
//@ComponentScan(basePackages = {"com.lab1Spring.musiquorum"})
//@ComponentScan(basePackages = {"com.lab1Spring.musiquorum.controllers",
//        "com.lab1Spring.musiquorum.services",
//        "com.lab1Spring.musiquorum.repositories",
//        "com.lab1Spring.musiquorum.configs",
//        "com.lab1Spring.musiquorum.security",
//        "com.lab1Spring.musiquorum.models",
//        "com.lab1Spring.musiquorum.exceptions",
//        "com.lab1Spring.musiquorum.errorHandlers",
//        "com.lab1Spring.musiquorum.dtos"})
public class MusiquorumApplication  {

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(MusiquorumApplication.class, args);
    }

}
