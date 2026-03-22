package com.product.manager.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.manager.model.Product;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.Optional;


@Repository //Marks this class as a repository component
public class ProductRepository {

    private final ObjectMapper mapper = new ObjectMapper(); //Used to convert (map) Java objects to JSON format
    private final File file; //JSON file used as data storage
    private final List<Product> products; //list of products

    //Default constructor used by the application
    public ProductRepository() {
        this("data/products.json");
    }

    //Constructor used mainly for testing with a custom file path
    public ProductRepository(String filepath) {
        try {
            file = new File(filepath);
            products = mapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error loading products JSON", e);
        }
    }

    //Convert/write product list to JSON file
    private void writeToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, products);
        } catch (Exception e) {
            throw new RuntimeException("Error writing JSON file", e);
        }
    }

    //Save a new product
    public Product save (Product product){
        products.add(product);
        writeToFile();
        return product;
    }

    //Return all stored products
    public List<Product> findAll(){
        return products;
    }

    //Search for a product by its id
    public Optional<Product> findById(Long id){
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    //Return all produts that match the given list of ids
    public  List<Product> findAllByIds(List<Long> ids){
        return products.stream()
                .filter(p -> ids.contains(p.getId()))
                .toList();
    }

    //Delete all products
    public void deleteAll(){
        products.clear();
        writeToFile();
    }

    //Delete a product by its id
    public void deleteById(Long id){
        products.removeIf(p -> p.getId().equals(id));
        writeToFile();
    }
}
