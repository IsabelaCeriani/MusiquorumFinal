package com.lab1Spring.musiquorum.dtos;

import com.lab1Spring.musiquorum.models.User;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.UUID;

public class UserDTO {


    private UUID id;

    private String username;

    @Email(message = "Por favor ingrese un email valido")
    private String email;
    private String profilePictureURL;


    private Date createdAt;
    private Date updatedAt;

    public UserDTO() {}

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public UserDTO(String name, String phoneNumber, String email, UUID id) {
        this.username = name;
        this.email = email;
        this.id = id;
    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.profilePictureURL = user.getProfilePictureURL();
        this.id = user.getId();
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }



    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
