package com.lab1Spring.musiquorum.controllers;

import com.lab1Spring.musiquorum.dtos.*;
import com.lab1Spring.musiquorum.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public ResponseEntity<UserDTO> logIn(@RequestBody @Valid LogInDTO data) {
        try {
            return ResponseEntity.ok( userService.logIn(data.getEmail(), data.getPassword()));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


//    @GetMapping(value = "/currentUser")
//    @ResponseBody
//    public String currentUserName(Authentication principal) { return principal.getName(); }
//
//
//    @GetMapping("/getCurrentUser")
//    public ResponseEntity<User> getCurrentUser() {
//        return ResponseEntity.ok(userService.getCurrentUser());
//    }
//
//    @GetMapping("/")
//    public UserDTO user(@AuthenticationPrincipal(errorOnInvalidType = true) User user) {
//        return new UserDTO(user);
//    }

    @GetMapping("/logout")
    public ResponseEntity logoutPage(HttpServletRequest request, HttpServletResponse response) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null){
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return ResponseEntity.ok().build();
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