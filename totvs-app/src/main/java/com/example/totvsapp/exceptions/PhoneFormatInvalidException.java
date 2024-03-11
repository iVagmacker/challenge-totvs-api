package com.example.totvsapp.exceptions;

/**
 * Exceção lançada quando um formato inválido é detectado para um número de
 * telefone.
 * Esta exceção estende RuntimeException, indicando que é uma exceção não
 * verificada.
 */
public class PhoneFormatInvalidException extends RuntimeException {

  /**
   * Construtor que cria uma instância de PhoneFormatInvalidException com uma
   * mensagem padrão.
   *
   * @param phoneNumber O número de telefone para o qual o formato é considerado
   *                    inválido.
   */
  public PhoneFormatInvalidException(String phoneNumber) {
    super("Formato inválido para o número de telefone: " + phoneNumber);
  }

  /**
   * Construtor que cria uma instância de PhoneFormatInvalidException com uma
   * mensagem personalizada.
   *
   * @param phoneNumber O número de telefone para o qual o formato é considerado
   *                    inválido.
   * @param message     Uma mensagem personalizada indicando detalhes específicos
   *                    sobre a exceção.
   */
  public PhoneFormatInvalidException(String phoneNumber, String message) {
    super("Formato inválido para o número de telefone " + phoneNumber + ": " + message);
  }
}