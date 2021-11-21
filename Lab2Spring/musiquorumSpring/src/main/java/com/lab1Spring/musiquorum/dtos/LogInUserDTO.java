package com.lab1Spring.musiquorum.dtos;

import javax.validation.constraints.NotNull;

public class LogInUserDTO {

    @NotNull
    private String username;
    @NotNull
    private String password;

    public LogInUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LogInUserDTO(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
