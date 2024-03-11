package com.example.totvsapp.infra;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Representa uma mensagem de erro padronizada para ser retornada em respostas
 * de APIs REST.
 * Esta classe é utilizada para encapsular informações sobre erros, incluindo o
 * status HTTP e a mensagem de erro.
 */
@AllArgsConstructor
@Getter
@Setter
public class RestErrorMessage {

  /**
   * O status HTTP associado à mensagem de erro.
   */
  private HttpStatus status;

  /**
   * A mensagem de erro detalhada.
   */
  private String message;
}