package com.product.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //Returns HTTP 400 when this exception is thrown
public class BadResourceRequestException extends RuntimeException {

    //Custom exception used for invalid client requests
    public BadResourceRequestException(String msg) {
        super(msg);
    }
}
