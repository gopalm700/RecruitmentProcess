package com.heavenhr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.heavenhr.exception.ConstraintsViolationException;
import com.heavenhr.exception.DataNotFoundException;


/**
 * @author gopal 20-Oct-2018
 *
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


  @ExceptionHandler(DataNotFoundException.class)
  public ResponseEntity<String> handleDataNotFound(DataNotFoundException e) {
    logger.error("Data notfound violation", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleInvalidDataException(MethodArgumentNotValidException e) {
    logger.error("INVALID DATA", e);
    return new ResponseEntity<>("Corrupted data", HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(ConstraintsViolationException.class)
  public ResponseEntity<String> handlePreconditionFailed(ConstraintsViolationException e) {
    logger.error("Constraint violation", e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception e) {
    logger.error("Internal server error! \n if the issue persists contact with gopalm700@gmail.com",
        e);
    return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

  }


}
