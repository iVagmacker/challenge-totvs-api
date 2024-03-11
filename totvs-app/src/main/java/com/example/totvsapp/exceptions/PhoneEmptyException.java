package com.example.totvsapp.exceptions;

/**
 * Exceção lançada quando um telefone é encontrado como vazio.
 * Esta exceção estende RuntimeException, indicando que é uma exceção não
 * verificada.
 */
public class PhoneEmptyException extends RuntimeException {

  /**
   * Construtor padrão que cria uma instância de PhoneEmptyException com uma
   * mensagem padrão.
   * A mensagem padrão é "Telefone não pode ser vazio".
   */
  public PhoneEmptyException() {
    super("Telefone não pode ser vazio");
  }

  /**
   * Construtor que cria uma instância de PhoneEmptyException com uma mensagem
   * personalizada.
   *
   * @param message Uma mensagem personalizada indicando detalhes específicos
   *                sobre a exceção.
   *                A mensagem padrão é "Telefone não pode ser vazio: " seguida
   *                pela mensagem personalizada.
   */
  public PhoneEmptyException(String message) {
    super("Telefone não pode ser vazio: " + message);
  }
}