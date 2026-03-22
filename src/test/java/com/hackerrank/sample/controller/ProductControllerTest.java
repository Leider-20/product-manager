package com.hackerrank.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.manager.controller.ProductController;
import com.product.manager.dto.ProductRequestDTO;
import com.product.manager.mapper.ProductMapper;
import com.product.manager.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class) //Loads only the web layer for testing the controller
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc; //Used to perform HTTP requests in tests

    @MockBean
    private ProductService service; //Mocked service dependency

    @MockBean
    private ProductMapper mapper; //Mocked mapper dependency

    @Autowired
    private ObjectMapper objectMapper; //Used to convert objects to JSON

    @Test
    void shouldSaveProduct() throws Exception {

        //Create a sample request DTO
        ProductRequestDTO request = new ProductRequestDTO();
        request.setId(1L);
        request.setName("Motorola");
        request.setDescription("Smartphone Motorola");
        request.setImageUrl("http://image.com/moto.jpg");
        request.setPrice(1000.0);
        request.setRating(4.5);

        //Perform POST request and expect HTTP 201
        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAllProducts() throws Exception {

        //Create sample DTOs
        ProductRequestDTO p1 = new ProductRequestDTO();
        p1.setName("Motorola");

        ProductRequestDTO p2 = new ProductRequestDTO();
        p2.setName("Samsung");

        //Mock service response
        when(service.getAllProducts()).thenReturn(List.of());

        //Make a GET request and wait for an HTTP 200 response
        mockMvc.perform(
                        get("/products")
                )
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnProductById() throws Exception {

        //Perform GET request for a specific product
        mockMvc.perform(get("/products/1")).andExpect(status().isOk());
    }

    @Test
    void shouldReturnProductsByIds() throws Exception {

        //Perform GET request with query parameters
        mockMvc.perform(get("/products/compare")
                        .param("ids", "1", "2"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteProductById() throws Exception {

        //Perform DELETE request for a specific product
        mockMvc.perform(delete("/products/1")).andExpect(status().isOk());
    }

    @Test
    void shouldDeleteAllProducts() throws Exception {

        //Perform DELETE request for all products
        mockMvc.perform(delete("/products")).andExpect(status().isOk());
    }

}
