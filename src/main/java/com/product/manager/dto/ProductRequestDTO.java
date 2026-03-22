package com.product.manager.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //Ignores unknown JSON fields
public class ProductRequestDTO {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Image URL cannot be empty")
    private String imageUrl;

    @NotBlank(message = "Descrption cannot be empty")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Rating cannot be null")
    @DecimalMin(value = "0.0", message = "Rating cannot be negative")
    @DecimalMax(value = "5.0", message = "Rating cannot be greater than 5")
    private Double rating;

    private Map<String, String> specifications;
}