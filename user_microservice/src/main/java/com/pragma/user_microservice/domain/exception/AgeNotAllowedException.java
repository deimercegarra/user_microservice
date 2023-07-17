package com.pragma.user_microservice.domain.exception;

public class AgeNotAllowedException extends RuntimeException {
    public AgeNotAllowedException() {
        super();
    }

    public AgeNotAllowedException(String message) {
        super(message);
    }

}
