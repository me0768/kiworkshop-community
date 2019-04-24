package org.kiworkshop.community.user.advice;

import org.kiworkshop.community.user.exception.PasswordMismatchException;
import org.kiworkshop.community.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserRestControllerAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public void userNotFoundExceptionHandler(UserNotFoundException ex) {
  }

  @ExceptionHandler(PasswordMismatchException.class)
  @ResponseStatus(code = HttpStatus.BAD_REQUEST)
  public void passwordMismatchExceptionHandler(PasswordMismatchException ex) {
  }
}
