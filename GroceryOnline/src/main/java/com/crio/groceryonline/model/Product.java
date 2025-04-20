package com.crio.groceryonline.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    
    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private Integer productId;

    @Column(name = "product_category")
    @NotBlank(message = "Category cannot be blank")
    private String productCategory;

    @Column(name = "product_price")
    @NotNull(message = "Price cannot be null")
    private Double productPrice;

    @Column(name = "product_quantity")
    @NotBlank(message = "Quantity cannot be blank")
    private String productQuantity;
}
