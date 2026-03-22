package com.hackerrank.sample.controller;

import java.util.List;

import com.hackerrank.sample.dto.ProductRequestDTO;
import com.hackerrank.sample.dto.ProductResponseDTO;
import com.hackerrank.sample.exception.BadResourceRequestException;
import com.hackerrank.sample.mapper.ProductMapper;
import com.hackerrank.sample.model.Product;
import com.hackerrank.sample.response.ApiResponse;
import com.hackerrank.sample.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  //Marks this class as a REST controller that handles HTTP requests
@RequestMapping("/products") //Base URL for all product endpoints
@RequiredArgsConstructor //Lombok generates constructor for dependency injection
public class ProductController {

    //Inject required dependencies
    private final ProductService productService;
    private final ProductMapper mapper;

    //Endpoint to create a new product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ProductResponseDTO> saveNewProduct(@RequestBody @Valid ProductRequestDTO productRequestDTO) {

        //Convert request DTO to entity
        Product product = mapper.toEntity(productRequestDTO);

        //Save product using service layer
        Product saved = productService.saveProduct(product);

        //Convert saved entity to response DTO
        ProductResponseDTO responseDTO = mapper.toDTO(saved);

        // Return API response with message and created product
        return new ApiResponse<>("Product successfully created", responseDTO);
    }

    //Endpoint to retrieve all products
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> getAllProducts() {
        return mapper.toDTOList(productService.getAllProducts());
    }

    //Endpoint to retrieve a product by id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        return mapper.toDTO(productService.getProductById(id));
    }

    //Endpoint to get multiple products, but by the id of each (this is for comparing products)
    @GetMapping("/compare")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponseDTO> compareProducts(@RequestParam List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BadResourceRequestException("At least one product id must be provided for comparison.");
        }
        return mapper.toDTOList(productService.getProductsByIds(ids));
    }

    //Endpoint to delete all products
    @DeleteMapping
    public ResponseEntity<String> deleteAllProducts() {
        productService.deleteAllProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Products successfully deleted.");
    }

    //Endpoint to delete a product by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Product successfully deleted.");
    }
}
