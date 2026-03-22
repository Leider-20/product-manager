package com.hackerrank.sample.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private Double price;

    private Double rating;

    private Map<String, String> specifications;
}