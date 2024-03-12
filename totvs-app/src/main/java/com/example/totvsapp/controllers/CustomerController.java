package com.example.totvsapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.totvsapp.domain.Customers.Customers;
import com.example.totvsapp.domain.Customers.CustomersDTO;
import com.example.totvsapp.services.CustomerService;

/**
 * Controller responsável por manipular operações relacionadas a clientes.
 * 
 * @RestController Indica que esta classe é um controlador Spring MVC.
 *                 @RequestMapping("/api/customers") Mapeia o caminho base para
 *                 todas as operações relacionadas a clientes.
 * @CrossOrigin(origins = "http://localhost:4200") Permite solicitações CORS do
 *                      servidor local de desenvolvimento.
 */
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

  @Autowired
  private CustomerService service;

  /**
   * Manipula solicitações HTTP POST para adicionar um novo cliente.
   * 
   * @param customersDTO Os dados do cliente a serem adicionados.
   * @return ResponseEntity<Customers> Um objeto ResponseEntity contendo o cliente
   *         recém-adicionado e o status HTTP correspondente.
   */
  @PostMapping
  public ResponseEntity<Customers> insert(@RequestBody CustomersDTO customersDTO) {
    Customers newCustomers = service.insert(customersDTO);
    return ResponseEntity.ok().body(newCustomers);
  }

  /**
   * Manipula solicitações HTTP GET para recuperar todos os clientes.
   * 
   * @return ResponseEntity<List<Customers>> Um objeto ResponseEntity contendo a
   *         lista de todos os clientes e o status HTTP correspondente.
   */
  @GetMapping
  public ResponseEntity<List<Customers>> getAll() {
    List<Customers> customers = this.service.getAll();
    return ResponseEntity.ok().body(customers);
  }

  /**
   * Manipula solicitações HTTP GET para recuperar um cliente pelo ID.
   * 
   * @param id O ID do cliente a ser recuperado.
   * @return ResponseEntity<Customers> Um objeto ResponseEntity contendo o cliente
   *         recuperado e o status HTTP correspondente.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Customers> getCustomerById(@PathVariable Long id) {
    Customers customers = this.service.getCustomerById(id);
    return ResponseEntity.ok().body(customers);
  }

  /**
   * Manipula solicitações HTTP PUT para atualizar um cliente existente.
   * 
   * @param id           O ID do cliente a ser atualizado.
   * @param customersDTO Os novos dados do cliente.
   * @return ResponseEntity<Customers> Um objeto ResponseEntity contendo o cliente
   *         atualizado e o status HTTP correspondente.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Customers> update(@PathVariable("id") Long id, @RequestBody CustomersDTO customersDTO) {
    Customers updatedCustomers = this.service.update(id, customersDTO);
    return ResponseEntity.ok().body(updatedCustomers);
  }

  /**
   * Manipula solicitações HTTP DELETE para excluir um cliente pelo ID.
   * 
   * @param id O ID do cliente a ser excluído.
   * @return ResponseEntity<Customers> Um objeto ResponseEntity com status
   *         noContent indicando que a exclusão foi bem-sucedida.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Customers> delete(@PathVariable("id") Long id) {
    this.service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
