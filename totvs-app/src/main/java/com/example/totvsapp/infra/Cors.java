package com.example.totvsapp.infra;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro CORS (Cross-Origin Resource Sharing) para permitir solicitações de
 * qualquer origem.
 * Esta classe estende a classe OncePerRequestFilter do Spring Framework.
 * 
 * <p>
 * Configura os cabeçalhos CORS necessários para permitir solicitações de
 * qualquer origem,
 * métodos permitidos (GET, POST, PUT, DELETE, OPTIONS), headers permitidos
 * (authorization, content-type, xsrf-token), desativa a autenticação de
 * credenciais
 * (Access-Control-Allow-Credentials: false) e define um tempo máximo de cache
 * para preflight requests.
 * </p>
 * 
 * <p>
 * Esta abordagem é mais permissiva e permite que a API seja acessada por
 * qualquer domínio.
 * Certifique-se de revisar e ajustar conforme necessário para atender aos
 * requisitos específicos
 * de segurança do seu projeto.
 * </p>
 * 
 */
@Component
public class Cors extends OncePerRequestFilter {

  /**
   * Método principal do filtro, onde são configurados os cabeçalhos CORS.
   * 
   * @param request     A solicitação HTTP recebida.
   * @param response    A resposta HTTP que será enviada.
   * @param filterChain O filtro da cadeia para continuar o processamento da
   *                    solicitação.
   * 
   * @throws ServletException Se ocorrer uma exceção durante o processamento do
   *                          filtro.
   * @throws IOException      Se ocorrer uma exceção de entrada/saída durante o
   *                          processamento do filtro.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
    response.setHeader("Access-Control-Allow-Credentials", "false");
    response.setHeader("Access-Control-Max-Age", "3600");
    filterChain.doFilter(request, response);
  }

}
