package com.hackerrank.sample.model;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id; //Unique identifier of the product

    private String name; //Product name

    private String imageUrl; //URL of the product image

    private String description; //Short description of the produt

    private Double price; //Product price

    private Double rating; //Product rating (0–5)

    private Map<String, String> specifications; //Aditional product specifications

}