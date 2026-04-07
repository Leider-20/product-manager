package com.product.manager.repository;

import com.product.manager.model.Product;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryTest {

    private ProductRepository repository;

    @BeforeEach
    void setup() {

        //Initialize repository with test JSON file
        repository = new ProductRepository("data/test-products.json");

        //Ensure repository starts empty before each test
        repository.deleteAll();
    }

    @Test
    void shouldSaveProduct() {

        //Create a product
        Product p = new Product();
        p.setId(1L);
        p.setName("Motorola");

        //Save product in repository
        Product saved = repository.save(p);

        //Verify product was saved
        assertNotNull(saved);

        //Verify correct id
        assertEquals(1L, saved.getId());

        //Verify repository contains one product
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void shouldReturnProductById() {

        //Create product
        Product p = new Product();
        p.setId(1L);
        p.setName("Motorola");

        //Save product
        repository.save(p);

        //Search product by id
        Optional<Product> result = repository.findById(1L);

        //Verify product exists
        assertTrue(result.isPresent());

        //Verify correct name
        assertEquals("Motorola", result.get().getName());
    }

    @Test
    void shouldReturnAllProducts(){

        //Create products
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Motorola");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Samsung");

        //Save products
        repository.save(p1);
        repository.save(p2);

        //Retrieve all products
        List<Product> products = repository.findAll();

        //Verify both products were returned
        assertEquals(2, products.size());
    }

    @Test
    void shouldReturnProductsByIds() {

        //Create products
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Motorola");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Samsung");

        Product p3 = new Product();
        p3.setId(3L);
        p3.setName("iPhone");

        //Save products
        repository.save(p1);
        repository.save(p2);
        repository.save(p3);

        //Retrieve products by specific ids
        List<Product> result = repository.findAllByIds(List.of(1L, 3L));

        //Verify correct number of products returned
        assertEquals(2, result.size());
    }

    @Test
    void shouldDeleteProductById(){

        //Create product
        Product product = new Product();
        product.setId(1L);
        product.setName("Motorola");

        //Save product
        repository.save(product);

        //Delete product by id
        repository.deleteById(1L);

        //Try to find deleted product
        Optional<Product> result = repository.findById(1L);

        //Verify product was removed
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldDeleteAllProducts(){

        //Create products
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Motorola");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Samsung");

        //Save products
        repository.save(p1);
        repository.save(p2);

        //Delete all products
        repository.deleteAll();

        //Retrieve products after deletion
        List<Product> products = repository.findAll();

        //Verify repository is empty
        assertTrue(products.isEmpty());
    }
}
