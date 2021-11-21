package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.EditUserDTO;
import com.lab1Spring.musiquorum.dtos.SignUpUserDTO;
import com.lab1Spring.musiquorum.dtos.UserDTO;
import com.lab1Spring.musiquorum.models.User;
import com.lab1Spring.musiquorum.services.UserService;
import com.sun.net.httpserver.HttpServer;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("users")
@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam String email, @RequestParam String password){
        try {
            return ResponseEntity.ok(userService.logIn(email, password)) ;
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/login")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return "redirect:/";
        }



        @PostMapping("")
    public ResponseEntity<UserDTO> signUp(@RequestBody @Valid SignUpUserDTO data) {
        return ResponseEntity.ok(userService.addUser(data));
    }

    @PutMapping("")
    public ResponseEntity<UserDTO> editUser(@RequestBody @Valid EditUserDTO data) {
        return ResponseEntity.ok(userService.editUser(data));
    }







//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(CreateUserDTO data) {
//        userService.updateUser(data);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<User> deleteUser(@PathVariable UUID id) {
//        userService.deleteUser(id);
//    }
//



}