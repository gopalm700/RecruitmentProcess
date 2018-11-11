package com.heavenhr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ConstraintsViolationException extends Exception {

  /**
  * 
  */
  private static final long serialVersionUID = 6586722879560982959L;

  public ConstraintsViolationException(String message) {
    super(message);
  }

  public ConstraintsViolationException(String message, Throwable t) {
    super(message, t);
  }

}
