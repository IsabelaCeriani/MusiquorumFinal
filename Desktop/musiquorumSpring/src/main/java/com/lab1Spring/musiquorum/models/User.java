package com.lab1Spring.musiquorum.models;

import com.lab1Spring.musiquorum.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private UUID id;

    @NotBlank
    private String username;


    @NotBlank
    private String email;

    @Lob
    private byte[] image;

    @NotBlank
    private String password;


    private boolean enabled = true;


    private Date createdAt;
    private Date updatedAt;

    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.password = "";
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    public String getEmail() {
        return email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public byte[] getProfilePicture() {
        return image;
    }

    public void setUsername(String name) {
        this.username = name;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicture(byte[] profilePictureURL) {
        this.image = profilePictureURL;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }




}
