package com.example.totvsapp.exceptions;

/**
 * Exceção lançada quando um cliente não é encontrado.
 * Esta exceção estende RuntimeException, indicando que é uma exceção não
 * verificada.
 */
public class CustomerNotFoundException extends RuntimeException {

  /**
   * Construtor padrão que cria uma instância de CustomerNotFoundException com uma
   * mensagem padrão.
   * A mensagem padrão é "Cliente não encontrado!".
   */
  public CustomerNotFoundException() {
    super("Cliente não encontrado!");
  }

  /**
   * Construtor que cria uma instância de CustomerNotFoundException com uma
   * mensagem personalizada.
   *
   * @param message Uma mensagem personalizada indicando detalhes específicos
   *                sobre a exceção.
   */
  public CustomerNotFoundException(String message) {
    super(message);
  }
}