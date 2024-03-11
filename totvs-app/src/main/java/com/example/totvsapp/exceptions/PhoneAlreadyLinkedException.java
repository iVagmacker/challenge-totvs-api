package com.example.totvsapp.exceptions;

/**
 * Exceção lançada quando um telefone já está vinculado a outro cliente.
 * Esta exceção estende RuntimeException, indicando que é uma exceção não
 * verificada.
 */
public class PhoneAlreadyLinkedException extends RuntimeException {

  /**
   * Construtor padrão que cria uma instância de PhoneAlreadyLinkedException com
   * uma mensagem padrão.
   * A mensagem padrão é "Telefone já vinculado a outro cliente".
   */
  public PhoneAlreadyLinkedException() {
    super("Telefone já vinculado a outro cliente");
  }

  /**
   * Construtor que cria uma instância de PhoneAlreadyLinkedException com uma
   * mensagem personalizada.
   *
   * @param message Uma mensagem personalizada indicando detalhes específicos
   *                sobre a exceção.
   */
  public PhoneAlreadyLinkedException(String message) {
    super(message);
  }
}