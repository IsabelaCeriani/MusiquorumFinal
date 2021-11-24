package com.lab1Spring.musiquorum.dtos;

import java.util.UUID;

public class UserWithTokenDTO extends UserDTO {

        private String token;

        public UserWithTokenDTO(String token) {
            this.token = token;
        }

        public UserWithTokenDTO(String name, String phoneNumber, String email, String token, UUID id) {
            super(name, phoneNumber, email, id);
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }

