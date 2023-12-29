package com.example.demo4.Exception;

public class ResourceNotFoundException extends RuntimeException  {


    public ResourceNotFoundException(String message) {
        super(message);
    }
}
