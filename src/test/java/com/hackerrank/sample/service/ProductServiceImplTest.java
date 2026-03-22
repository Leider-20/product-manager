package com.hackerrank.sample.service;

import com.hackerrank.sample.model.Product;
import com.hackerrank.sample.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //Enable Mockito support for unit tests
public class ProductServiceImplTest {

    @Mock
    private ProductRepository repository; //Mock repository dependency

    @InjectMocks
    private ProductServiceImpl service; //Inject mocked repository into service

    @Test
    void shouldSaveProduct(){

        //Create product
        Product product = new Product();
        product.setId(1L);
        product.setName("Motorola");

        //Mock repository save behavior
        when(repository.save(product)).thenReturn(product);

        //Call service method
        Product saved = service.saveProduct(product);

        //Verify product was returned
        assertNotNull(saved);

        //Verify product name
        assertEquals("Motorola", saved.getName());

        //Verify repository save was called
        verify(repository).save(product);
    }

    @Test
    void shouldReturnProductById() {

        //Create product
        Product product = new Product();
        product.setId(1L);
        product.setName("Motorola");

        //Mock repository response
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        //Call service method
        Product result = service.getProductById(1L);

        //Verify returned product
        assertEquals("Motorola", result.getName());

        //Verify repository method call
        verify(repository).findById(1L);

    }

    @Test
    void shouldReturnAllProducts(){

        //Create products
        Product p1 = new Product();
        p1.setId(1L);
        p1.setName("Motorola");

        Product p2 = new Product();
        p2.setId(2L);
        p2.setName("Honor");

        //Mock repository response
        when(repository.findAll()).thenReturn(List.of(p1, p2));

        //Call service method
        List<Product> products = service.getAllProducts();

        //Verify number of products returned
        assertEquals(2, products.size());

        //Verify repository method call
        verify(repository).findAll();
    }

    @Test
    void shouldCompareProducts() {

        //Create products
        Product p1 = new Product();
        p1.setId(1L);

        Product p2 = new Product();
        p2.setId(2L);

        //Mock repository response
        when(repository.findAllByIds(List.of(1L, 2L))).thenReturn(List.of(p1, p2));

        //Call service method
        List<Product> result = service.getProductsByIds(List.of(1L,2L));

        //Verify correct number of products
        assertEquals(2, result.size());

    }

    @Test
    void shouldDeleteProductById(){

        //Sample product id
        Long id = 1L;

        //Mock delete operation
        doNothing().when(repository).deleteById(id);

        //Call service method
        service.deleteProductById(id);

        //Verify delete method was called
        verify(repository).deleteById(id);
    }

    @Test
    void shouldDeleteAllProducts(){

        //Mock delete all operation
        doNothing().when(repository).deleteAll();

        //Call service method
        service.deleteAllProducts();

        //Verify delete method was called
        verify(repository).deleteAll();

    }

}
