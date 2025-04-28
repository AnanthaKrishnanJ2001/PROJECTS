package com.rentvideo.RentVideo.Service;

import com.rentvideo.RentVideo.DTO.Request.AuthRequest;
import com.rentvideo.RentVideo.DTO.Request.RegisterRequest;
import com.rentvideo.RentVideo.DTO.Response.AuthResponse;

public interface Authservice {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
