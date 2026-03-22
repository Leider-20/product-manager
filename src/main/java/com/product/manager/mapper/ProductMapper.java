package com.product.manager.mapper;

import com.product.manager.dto.ProductRequestDTO;
import com.product.manager.dto.ProductResponseDTO;
import com.product.manager.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

//MapStruct will generate the implementation and register it as a Spring bean
@Mapper(componentModel = "spring")
public interface ProductMapper {

    //Converts a request DTO into a Product entity
    Product toEntity(ProductRequestDTO productRequestDTO);

    //Converts a Product entity into a response DTO
    ProductResponseDTO toDTO(Product product);

    //Converts a list of Product entities into a list of response DTOs
    List<ProductResponseDTO> toDTOList(List<Product> products);

}