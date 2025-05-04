package com.rentvideo.RentVideo.DTO.Request;

import com.rentvideo.RentVideo.Model.Enums.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "First Name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank")
    private String lastName;
    @NotBlank(message = "Email cannot be blank")
    private String email;
    private String password;
    private Role role;
}
