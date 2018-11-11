package com.heavenhr.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataNotFoundException extends Exception {
  private static final long serialVersionUID = -6580368340016624447L;

  public DataNotFoundException(String message) {
    super(message);
  }

  public DataNotFoundException(String message, Throwable t) {
    super(message, t);
  }
}

