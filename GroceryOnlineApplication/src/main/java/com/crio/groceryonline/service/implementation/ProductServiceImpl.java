package com.crio.groceryonline.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.groceryonline.exception.ResourceNotFoundException;
import com.crio.groceryonline.model.Product;
import com.crio.groceryonline.repository.ProductRepository;
import com.crio.groceryonline.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepositoty;

    @Override
    public Product createProduct(Product product) {
        return productRepositoty.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Product product = productRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepositoty.delete(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositoty.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Integer id, Product productDetails) {
        Product product = productRepositoty.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.setProductPrice(productDetails.getProductPrice());
        product.setProductCategory(productDetails.getProductCategory());
        product.setProductQuantity(productDetails.getProductQuantity());
        productRepositoty.save(product);
        return product;
    }
    
}
