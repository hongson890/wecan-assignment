package com.wecan.assignment.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VoucherExceptionControllerAdvice {
    @ExceptionHandler(value = VoucherNotfoundException.class)
    public ResponseEntity<Object> exception(VoucherNotfoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = VoucherExistedException.class)
    public ResponseEntity<Object> exception(VoucherExistedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = VoucherBadParamsException.class)
    public ResponseEntity<Object> exception(VoucherBadParamsException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
