package com.rentvideo.RentVideo.Service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentvideo.RentVideo.DTO.Response.AuthResponse;
import com.rentvideo.RentVideo.Exception.RegistrationFailedException;
import com.rentvideo.RentVideo.Exception.ResourceNotFoundException;
import com.rentvideo.RentVideo.Model.User;
import com.rentvideo.RentVideo.Model.Enums.Role;
import com.rentvideo.RentVideo.Repository.UserRepository;
import com.rentvideo.RentVideo.Service.Authservice;
import com.rentvideo.RentVideo.Service.JWTService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import com.rentvideo.RentVideo.DTO.Request.*;;

@Service
public class AuthServiceImpl implements Authservice{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @PersistenceContext
    private EntityManager entityManager;

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
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

    try {
        userRepository.save(user);
    } catch (Exception e) {
        throw new RegistrationFailedException("Registration failed ");
    }

    String jwtToken = jwtService.generateToken(user);

    return AuthResponse.builder()
            .accessToken(jwtToken)
            .build();
}


    @Override
    public AuthResponse login(AuthRequest request) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

       User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("USER NOT FOUND"));

       entityManager.detach(user); // ensures it's not managed anymore

       String jwtToken = jwtService.generateToken(user);
       return AuthResponse.builder()
                        .accessToken(jwtToken)
                        .build();
    }
    
}
