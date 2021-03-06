package com.lab1Spring.musiquorum.services;

import com.lab1Spring.musiquorum.dtos.EditUserDTO;
import com.lab1Spring.musiquorum.dtos.PendingAssignmentDTO;
import com.lab1Spring.musiquorum.dtos.UserDTO;
import com.lab1Spring.musiquorum.dtos.SignUpUserDTO;
import com.lab1Spring.musiquorum.exceptions.BadRequestException;
import com.lab1Spring.musiquorum.models.*;
import com.lab1Spring.musiquorum.models.Class;
import com.lab1Spring.musiquorum.repositories.*;
import javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Collections.emptyList;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

//    @Autowired
    private PasswordEncoder passwordEncoder= new BCryptPasswordEncoder();




    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        userRepository.findAll().forEach(usersList::add);
        return usersList;
    }




    //retorna user con nombre de usuario y tira excepcion si el nombre no es encontrado o la contrasena es incorrecta
    public UserDTO logIn(String email, String password) throws NotFoundException {
        final Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent() && isCorrectPassword(email, password)) {
            return new UserDTO(optionalUser.get());
        }
        else {
            throw new NotFoundException("Invalid email or password");
        }
    }


    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return userRepository.findByUsername(name).orElseThrow(() -> new BadRequestException("No se ha podido encontrar al usuario"));

    }



//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       User user = userRepository.findByUsername(username).orElseThrow(() -> new BadRequestException(""));
//
//        if (user != null) {
//            return new User();
//        } else {
//            throw new UsernameNotFoundException(format("User %s does not exist!", username));
//        }
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
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
        if(emailExists(data.getEmail())) throw new BadRequestException("Email: " + data.getEmail() + " is already being used"); //return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
        if(userNameExists(data.getUsername())) throw new BadRequestException("Username " + data.getUsername() + " is already taken");//return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
        if(data.getPassword().length()<8) throw new BadRequestException("Password must have at least eight characters");
    }

    public boolean isCorrectPassword(String email, String password) {
        final Optional<User> user = userRepository.findByEmail(email);//se podria pasar el User para no repetir este paso
        return user.map(u -> passwordEncoder.matches(password, u.getPassword())).orElseThrow(() -> new BadRequestException("User not found"));
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
        if(password.length()<8)throw new BadRequestException("Contrase??a debe tener al menos 8 caracteres ");
        if(!email.contains("@")) throw new BadRequestException("Por favor ingrese un email valido");

    }

    private void checkValidPasswordChange(EditUserDTO data, User user){
        if(data.getNewPassword()==(null) && data.getPreviousPassword()==(null)) return;
        if(data.getNewPassword()!=(null) && data.getPreviousPassword()==(null)) throw new BadRequestException("El cambio de contrase??a requiere que se ingrese la anterior");
        if(data.getNewPassword()!=(null) && data.getPreviousPassword()!=(null)) {
            if(data.getNewPassword().length()<8) throw new BadRequestException("Contrase??a debe tener al menos 8 caracteres ");
            if(!isCorrectPassword(data.getUsername(), data.getPreviousPassword())) throw new BadRequestException("Contrase??a incorrecta");
            if(isCorrectPassword(data.getUsername(), data.getPreviousPassword()))  user.setPassword(passwordEncoder.encode(data.getNewPassword()));
            if(userRepository.findByEmail(data.getEmail()).isPresent() && userRepository.findByEmail(data.getEmail()).get().getPassword().equals(data.getPreviousPassword())) user.setPassword(passwordEncoder.encode(data.getNewPassword()));
        }
    }


    public List<PendingAssignmentDTO> getPendingAssignments(UUID userId) {
        List<PendingAssignmentDTO> response = new ArrayList<>();
        List<Homework> pendingHomeworks =homeworkRepository.findByUserid(userId).stream().filter(h -> h.getStatus().equals(HomeworkStatus.NOT_SUBMITTED)).collect(Collectors.toList());
        for (Homework homework : pendingHomeworks) {
            Course course  = homework.getAssignment().getClasss().getCourse();
            response.add(new PendingAssignmentDTO(course.getId(), course.getName(), homework.getAssignment()));
        }
        return response;
}

    public User uploadPic(UUID userId, MultipartFile file) {
        User user = userRepository.findById(userId).orElseThrow(() -> new BadRequestException("User not found"));
        if(!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                user.setProfilePicture(bytes);
                userRepository.save(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}