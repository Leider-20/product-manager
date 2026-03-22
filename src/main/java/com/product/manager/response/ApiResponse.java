package com.product.manager.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

    private String message; //Message describing the result of the request
    private T data; // Generic field for response data
}
