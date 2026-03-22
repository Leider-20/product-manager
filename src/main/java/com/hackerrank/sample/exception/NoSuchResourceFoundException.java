package com.hackerrank.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //Returns HTTP 404 when this exception is thrown
public class NoSuchResourceFoundException extends RuntimeException {

    //Custom exception used when a requested resource is not found
    public NoSuchResourceFoundException(String msg) {
        super(msg);
    }
}
