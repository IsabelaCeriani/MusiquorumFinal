package com.lab1Spring.musiquorum.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class SignUpUserDTO {

    @NotEmpty(message = "First name can not be empty")
    @NotNull
    private String username;



    @NotEmpty(message = "Email can not be empty")
    @Email(message = "Please provide a valid email")
    private String email;


    private String profilePictureURL;



    private boolean isActive;

    @NotEmpty(message = "Password can not be empty")
    private String password;

    private Date createdAt;
    private Date updatedAt;


    public SignUpUserDTO() {}

    public SignUpUserDTO(String username,  String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = true;
    }

    public String getUsername() {
        return username;
    }


    public String getEmail() {
        return email;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
