package com.lab1Spring.musiquorum.services;

import com.lab1Spring.musiquorum.dtos.EditUserDTO;
import com.lab1Spring.musiquorum.dtos.UserDTO;
import com.lab1Spring.musiquorum.dtos.SignUpUserDTO;
import com.lab1Spring.musiquorum.exceptions.AuthenticationException;
import com.lab1Spring.musiquorum.exceptions.BadRequestException;
import com.lab1Spring.musiquorum.models.User;
import com.lab1Spring.musiquorum.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import  com.lab1Spring.musiquorum.exceptions.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Validated
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;



    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());


    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().forEach(usersList::add);
        return usersList;
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        try {
            return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("No se ha podido encontrar al usuario"));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }




    //retorna user con nombre de usuario y tira excepcion si el nombre no es encontrado o la contrasena es incorrecta
    public User logIn(String email, String password) throws NotFoundException {
//        final Optional<User> optionalUser = userRepository.findByEmail(email);
//        if (optionalUser.isPresent() && isCorrectPassword(email, password)) {
//            return optionalUser.get();
//        }
//        else {
//            throw new AuthenticationException("Invalid email or password");
//        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                email, password
        );

        Authentication authenticate = authenticationManager.authenticate(token);

        return (User) authenticate.getPrincipal();
    }


    public UserDTO addUser(SignUpUserDTO data) {
        checkValidData(data);

        User user = new User();
        BeanUtils.copyProperties(data, user);
        encodePassword(user, data);
        userRepository.save(user);


        UserDTO response = new UserDTO();
        BeanUtils.copyProperties(user, response);

        return response; //ACCEPTED

    }




    private boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private boolean userNameExists(String name) {
        return userRepository.findByUsername(name).isPresent();
    }

    private void encodePassword(User userEntity, SignUpUserDTO user){
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
    }
    private void checkValidData(SignUpUserDTO data) {
        if(emailExists(data.getEmail())) throw new IllegalArgumentException("Email: " + data.getEmail() + " is already being used"); //return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
        if(userNameExists(data.getUsername())) throw new IllegalArgumentException("Username " + data.getUsername() + " is already taken");//return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
        if(data.getPassword().length()<8) throw new IllegalArgumentException("Password must have at least eight characters");
    }

    public boolean isCorrectPassword(String email, String password) {
        final Optional<User> user = userRepository.findByEmail(email);//se podria pasar el User para no repetir este paso
        return user.map(u -> passwordEncoder.matches(password, u.getPassword())).get();
    }



    public UserDTO editUser(EditUserDTO data) {
        User user = userRepository.findByEmail(data.getEmail()).orElseThrow(()-> new BadRequestException("Could not find user"));

        data.setPassword(user.getPassword());
        checkEditUserValidData(data.getEmail(), data.getPassword(), user);
        BeanUtils.copyProperties(data, user);
        checkValidPasswordChange(data, user);
        userRepository.save(user);

        UserDTO response = new UserDTO();
        BeanUtils.copyProperties(user, response);


        return response; //ACCEPTED
    }

    private void checkEditUserValidData(String email, String password,User user) {
        if(userRepository.findByEmail(email).isPresent() && !Objects.equals(userRepository.findByEmail(email).get().getUsername(), user.getUsername()))throw new BadRequestException("Email: " + email + " ya se encuentra en uso");
        if(password.length()<8)throw new BadRequestException("Contraseña debe tener al menos 8 caracteres ");
        if(!email.contains("@")) throw new BadRequestException("Por favor ingrese un email valido");

    }

    private void checkValidPasswordChange(EditUserDTO data, User user){
        if(data.getNewPassword()==(null) && data.getPreviousPassword()==(null)) return;
        if(data.getNewPassword()!=(null) && data.getPreviousPassword()==(null)) throw new BadRequestException("El cambio de contraseña requiere que se ingrese la anterior");
        if(data.getNewPassword()!=(null) && data.getPreviousPassword()!=(null)) {
            if(data.getNewPassword().length()<8) throw new BadRequestException("Contraseña debe tener al menos 8 caracteres ");
            if(!isCorrectPassword(data.getUsername(), data.getPreviousPassword())) throw new BadRequestException("Contraseña incorrecta");
            if(isCorrectPassword(data.getUsername(), data.getPreviousPassword()))  user.setPassword(passwordEncoder.encode(data.getNewPassword()));
            if(userRepository.findByEmail(data.getEmail()).isPresent() && userRepository.findByEmail(data.getEmail()).get().getPassword().equals(data.getPreviousPassword())) user.setPassword(passwordEncoder.encode(data.getNewPassword()));
        }
    }


}