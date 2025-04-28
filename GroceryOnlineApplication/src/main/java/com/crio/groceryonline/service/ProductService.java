package com.crio.groceryonline.service;

import java.util.List;

import com.crio.groceryonline.model.Product;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Integer id);

    Product createProduct(Product product);

    Product updateProduct(Integer id, Product productDetails);


    void deleteProduct(Integer id);
    
}
