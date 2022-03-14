package com.amore.cash.config;

import com.amore.cash.packet.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Exception Handler
 */
@Slf4j
@RestControllerAdvice
public class CashExceptionHandler {

    /**
     * @date : 2022-03-14 오후 2:49
     * @description : 요청내역에 대한 걸과가 없을 때
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {

        return ResponseEntity
                .ok()
                .body(this.makeResponse(HttpStatus.NO_CONTENT));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> IllegalArgumentExceptionException(IllegalArgumentException e) {

        return ResponseEntity
                .ok()
                .body(this.makeResponse(HttpStatus.BAD_REQUEST));
    }

    /**
     * @date : 2022-03-14 오후 2:49
     * @description : Exception 에 대한 공통 응답객체 Builder
     */
    private ExceptionResponse makeResponse(HttpStatus httpStatus) {
        return ExceptionResponse.builder()
                .statusCode(httpStatus.value())
                .message(httpStatus.getReasonPhrase())
                .dateTime(LocalDateTime.now())
                .build();
    }

}




