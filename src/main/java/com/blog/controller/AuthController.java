package com.blog.controller;

import com.blog.entity.Role;
import com.blog.entity.User;
import com.blog.payload.LoginDto;
import com.blog.payload.SignUpDto;
import com.blog.repository.RoleRepository;
import com.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public  ResponseEntity<String > authenticateuser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("user is sign in successfull",HttpStatus.OK);

    }




    @PostMapping("/signup")
    public ResponseEntity<String > signUpUser(@RequestBody SignUpDto signUpDto){
if(userRepository.existsByUsername(signUpDto.getUsername())){
    return new ResponseEntity<>("username is already taken",HttpStatus.BAD_REQUEST);
}
if(userRepository.existsByEmail(signUpDto.getEmail())){
    return new ResponseEntity<>("email is already taken",HttpStatus.BAD_REQUEST);
}


        User user=new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        Role role =roleRepository.findByName("ROLE_ADMIN").get();
user.setRoles(Collections.singleton(role));
        User saved = userRepository.save(user);
return new ResponseEntity<>("user sign up successfull", HttpStatus.OK);
    }
}
