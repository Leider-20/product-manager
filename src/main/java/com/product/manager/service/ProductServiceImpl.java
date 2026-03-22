package com.product.manager.service;

import com.product.manager.exception.BadResourceRequestException;
import com.product.manager.exception.NoSuchResourceFoundException;
import com.product.manager.model.Product;
import com.product.manager.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //Marks this class as a service component
@RequiredArgsConstructor //Lombok generates constructor for dependency injection
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository; //Repository used to access product data

    //Save the new product
    @Override
    public Product saveProduct(Product product) {

        //Check if a product with the same id already exists
        Optional<Product> existingProduct = productRepository.findById(product.getId());

        //Prevent creating duplicated products
        if (existingProduct.isPresent()) {
            throw new BadResourceRequestException("Product with same id exists.");
        }
        return productRepository.save(product);
    }

    //Return a product that matches the ID provided or throw exception if not found
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchResourceFoundException("No product with given id found."));
    }

    //Return all stored products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //Return products that match the given ids
    @Override
    public List<Product> getProductsByIds(List<Long> ids) {
        return productRepository.findAllByIds(ids);
    }

    //Delete a specific product by id
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    //Delete all products
    @Override
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }


}
