package org.carlos.picpaychallenge.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handleInvalidTransaction(InvalidTransactionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}