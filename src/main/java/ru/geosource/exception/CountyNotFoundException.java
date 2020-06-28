package ru.geosource.exception;

public class CountyNotFoundException extends RuntimeException {

    public CountyNotFoundException(String message) {
        super(message);
    }
}
