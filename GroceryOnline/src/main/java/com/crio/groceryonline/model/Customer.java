package com.crio.groceryonline.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue
    private Integer customerId;

    @Column(name = "customer_name")
    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    @Column(name = "customer_email")
    @Email(message = "Email should be valid")
    private String customerEmail;

    @Column(name = "customer_address")
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @Column(name = "customer_phone_number")
    @NotBlank(message = "customer number cannot be blank")
    private String phoneNumber;
}
