package com.lab1Spring.musiquorum.dtos;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class EditUserDTO {


    @NotBlank(message = "Por favor ingrese un su nombre")
    private String username;


    @NotBlank(message = "Por favor ingrese un su email")
    @Email(message = "Por favor ingrese un email valido")
    private String email;


    private String profilePictureURL;

    @Size(min = 8, message = "Contraseña debe tener al menos 8 caracteres ")
    private String password;

    private String newPassword;
    private  String previousPassword;

    private Date createdAt;
    private Date updatedAt;


    public EditUserDTO() {}



    public EditUserDTO(String username, String email, String profilePictureURL) {
        this.username = username;
        this.email = email;
        this.profilePictureURL = profilePictureURL;
        this.password= "";

    }



    public EditUserDTO(String username, String email, String profilePictureURL, String previousPassword, String newPassword) {
        this.username = username;
        this.profilePictureURL = profilePictureURL;
        this.email = email;
        this.previousPassword =previousPassword;
        this.password = "";
        this.newPassword = newPassword;
    }
    public EditUserDTO(String username, String email) {
        this.username = username;
        this.profilePictureURL = "";
        this.email = email;
        this.previousPassword ="";
        this.password = "";
        this.newPassword = "";
    }



    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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


    public String getNewPassword() {
        return newPassword;
    }

    public String getPreviousPassword() {
        return previousPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setPreviousPassword(String previousPassword) {
        this.previousPassword = previousPassword;
    }

}

