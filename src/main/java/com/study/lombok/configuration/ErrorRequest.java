package com.study.lombok.configuration;

public class ErrorRequest extends RuntimeException{

    public ErrorRequest(String message) {
        super(message);
    }
}
