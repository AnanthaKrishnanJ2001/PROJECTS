package com.rentvideo.RentVideo.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentvideo.RentVideo.DTO.Response.AuthResponse;
import com.rentvideo.RentVideo.Exception.RegistrationFailedException;
import com.rentvideo.RentVideo.Model.User;
import com.rentvideo.RentVideo.Model.Enums.Role;
import com.rentvideo.RentVideo.Repository.UserRepository;
import com.rentvideo.RentVideo.Service.Authservice;
import com.rentvideo.RentVideo.DTO.Request.*;;

@Service
public class AuthServiceImpl implements Authservice{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    // @Override
    // public AuthResponse register(RegisterRequest request) {
        
    //     if(request.getRole() == null){
    //         request.setRole(Role.USER);
    //     }

    //     User user = User.builder()
    //                     .name(request.getName())
    //                     .email(request.getEmail())
    //                     .password(passwordEncoder.encode(request.getPassword()))
    //                     .role(request.getRole())
    //                     .build();

    //     userRepository.save(user);

    //     return AuthResponse.builder().build();

    // }

    @Override
public AuthResponse register(RegisterRequest request) {
    if (request.getRole() == null) {
        request.setRole(Role.CUSTOMER);
    }

    User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

    try {
        userRepository.save(user);
    } catch (Exception e) {
        throw new RegistrationFailedException("Registration failed ");
    }

    return AuthResponse.builder()
            .message("User registered successfully!")
            .build();
}


    @Override
    public AuthResponse login(AuthRequest request) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
       return AuthResponse.builder().build();
    }
    
}
