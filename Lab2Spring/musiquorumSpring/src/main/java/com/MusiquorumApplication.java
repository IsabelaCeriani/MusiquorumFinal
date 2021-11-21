package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@ComponentScan(basePackages = {"com.lab1Spring.musiquorum.controllers", "com.lab1Spring.musiquorum.services", "com.lab1Spring.musiquorum.repositories", "com.lab1Spring.musiquorum.configs"})
@EnableJpaRepositories(basePackages = "com.lab1Spring.musiquorum.repositories")
public class MusiquorumApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MusiquorumApplication.class, args);
    }

}
