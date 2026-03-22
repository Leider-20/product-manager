package com.product.manager.service;

import com.product.manager.model.Product;

import java.util.List;

public interface ProductService {

    //Creates and stores a new product
    Product saveProduct(Product product);

    //Retrieves a product by its id
    Product getProductById(Long id);

    //Retrieves all available products
    List<Product> getAllProducts();

    //Retrieves products that match the given ids
    List<Product> getProductsByIds(List<Long> ids);

    //Deletes a specific product by id
    void deleteProductById(Long id);

    //Deletes all stored products
    void deleteAllProducts();
}
