package ru.geosource.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String STATE_NOT_FOUND_MESSAGE = "Объект по области не найден";
    private static final String COUNTY_NOT_FOUND_MESSAGE = "Объект по федеральному округу не найден";
    private static final String INTERNAL_SERVER_ERROR_MESSAGE = "Внутренняя ошибка сервиса";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {

        if (ex instanceof StateNotFoundException) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(STATE_NOT_FOUND_MESSAGE);
        } else if (ex instanceof CountyNotFoundException) {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(COUNTY_NOT_FOUND_MESSAGE);
        } else {
            log.error(ex.getMessage(), ex);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }
}
