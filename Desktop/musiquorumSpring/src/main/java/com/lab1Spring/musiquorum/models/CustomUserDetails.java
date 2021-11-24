package com.lab1Spring.musiquorum.models;

import org.assertj.core.util.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails extends User implements UserDetails {

    public CustomUserDetails() {

    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return (Collection<? extends GrantedAuthority>) Sets.newHashSet(new SimpleGrantedAuthority("ROLE_"));
//    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}