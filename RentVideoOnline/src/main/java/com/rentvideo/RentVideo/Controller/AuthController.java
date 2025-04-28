package com.rentvideo.RentVideo.Controller;

import java.rmi.registry.Registry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentvideo.RentVideo.DTO.Request.AuthRequest;
import com.rentvideo.RentVideo.DTO.Request.RegisterRequest;
import com.rentvideo.RentVideo.DTO.Response.AuthResponse;
import com.rentvideo.RentVideo.Service.Authservice;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/video-rental")
public class AuthController {
    @Autowired
    Authservice authservice;
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request){

        return ResponseEntity.ok(authservice.register(request));
    }

     @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authservice.login(request));
    }
}
