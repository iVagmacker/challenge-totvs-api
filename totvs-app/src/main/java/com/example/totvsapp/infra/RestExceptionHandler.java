package com.example.totvsapp.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.totvsapp.exceptions.CustomerNotFoundException;
import com.example.totvsapp.exceptions.PhoneAlreadyLinkedException;
import com.example.totvsapp.exceptions.PhoneEmptyException;
import com.example.totvsapp.exceptions.PhoneFormatInvalidException;

/**
 * Uma classe de controle de exceções para lidar com exceções específicas e
 * fornecer respostas padronizadas em uma API REST.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Manipula exceções do tipo CustomerNotFoundException.
   *
   * @param exception A exceção lançada.
   * @return ResponseEntity contendo um objeto RestErrorMessage com status
   *         NOT_FOUND e a mensagem de erro.
   */
  @ExceptionHandler(CustomerNotFoundException.class)
  private ResponseEntity<RestErrorMessage> customerNotFoundHandler(CustomerNotFoundException exception) {
    RestErrorMessage response = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  /**
   * Manipula exceções do tipo PhoneAlreadyLinkedException.
   *
   * @param exception A exceção lançada.
   * @return ResponseEntity contendo um objeto RestErrorMessage com status
   *         BAD_REQUEST e a mensagem de erro.
   */
  @ExceptionHandler(PhoneAlreadyLinkedException.class)
  private ResponseEntity<RestErrorMessage> phoneAlreadyLinkedHandler(PhoneAlreadyLinkedException exception) {
    RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /**
   * Manipula exceções do tipo PhoneFormatInvalidException.
   *
   * @param exception A exceção lançada.
   * @return ResponseEntity contendo um objeto RestErrorMessage com status
   *         BAD_REQUEST e a mensagem de erro.
   */
  @ExceptionHandler(PhoneFormatInvalidException.class)
  private ResponseEntity<RestErrorMessage> invalidPhoneNumberFormatHandler(
      PhoneFormatInvalidException exception) {
    RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  /**
   * Manipula exceções do tipo PhoneEmptyException.
   *
   * @param exception A exceção lançada.
   * @return ResponseEntity contendo um objeto RestErrorMessage com status
   *         BAD_REQUEST e a mensagem de erro.
   */
  @ExceptionHandler(PhoneEmptyException.class)
  private ResponseEntity<RestErrorMessage> emptyPhoneNumberHandler(PhoneEmptyException exception) {
    RestErrorMessage response = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }
}