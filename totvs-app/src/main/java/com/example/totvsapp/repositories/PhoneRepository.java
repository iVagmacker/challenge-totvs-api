package com.example.totvsapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.totvsapp.domain.Phones.Phones;

@Repository
public interface PhoneRepository extends JpaRepository<Phones, Long> {

  /**
   * Encontra um objeto Phones pelo número de telefone.
   *
   * @param number O número de telefone a ser pesquisado.
   * @return O objeto Phones correspondente ao número de telefone, ou null se não
   *         encontrado.
   */
  Phones findByNumber(String number);

  /**
   * Encontra todos os objetos Phones associados a um cliente pelo ID do cliente.
   *
   * @param id O ID do cliente para o qual os números de telefone estão
   *           associados.
   * @return Uma lista de objetos Phones associados ao cliente, ou uma lista vazia
   *         se nenhum for encontrado.
   */
  List<Phones> findByCustomersId(Long id);
}
