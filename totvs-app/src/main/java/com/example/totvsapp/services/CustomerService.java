package com.example.totvsapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.totvsapp.domain.Customers.Customers;
import com.example.totvsapp.domain.Customers.CustomersDTO;
import com.example.totvsapp.domain.Phones.Phones;
import com.example.totvsapp.exceptions.CustomerNotFoundException;
import com.example.totvsapp.exceptions.PhoneAlreadyLinkedException;
import com.example.totvsapp.exceptions.PhoneEmptyException;
import com.example.totvsapp.exceptions.PhoneFormatInvalidException;
import com.example.totvsapp.repositories.CustomerRepository;
import com.example.totvsapp.repositories.PhoneRepository;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private PhoneRepository phoneRepository;

  /**
   * Insere um novo cliente no sistema.
   *
   * @param customersDTO Os dados do cliente a serem inseridos.
   * @return O cliente recém-criado.
   * @throws PhoneEmptyException         Se a lista de números de telefone estiver
   *                                     vazia ou nula.
   * @throws PhoneFormatInvalidException Se algum número de telefone tiver um
   *                                     formato inválido.
   * @throws PhoneAlreadyLinkedException Se algum número de telefone já estiver
   *                                     vinculado a outro cliente.
   */
  @Transactional
  public Customers insert(CustomersDTO customersDTO) {
    try {
      validatePhones(customersDTO.phones());

      Customers newCustomers = new Customers(customersDTO);
      List<Phones> phones = createPhoneEntities(newCustomers, customersDTO.phones());

      newCustomers.setPhones(phones);
      customerRepository.save(newCustomers);

      phones.forEach(phone -> phone.setCustomers(newCustomers));
      phoneRepository.saveAll(phones);

      return newCustomers;
    } catch (PhoneEmptyException | PhoneFormatInvalidException | PhoneAlreadyLinkedException e) {
      throw new RuntimeException("Erro ao salvar cliente", e);
    }
  }

  /**
   * Obtém todos os clientes no sistema.
   *
   * @return Uma lista de todos os clientes.
   */
  public List<Customers> getAll() {
    List<Customers> customers = customerRepository.findAll();
    customers.forEach(customer -> {
      List<Phones> phones = phoneRepository.findByCustomersId(customer.getId());
      customer.setPhones(phones);
    });

    return customers;
  }

  /**
   * Exclui um cliente do sistema.
   *
   * @param id O ID do cliente a ser excluído.
   * @throws CustomerNotFoundException Se o cliente não for encontrado.
   */
  @Transactional
  public void delete(Long id) {
    Customers customers = customerRepository.findById(id)
        .orElseThrow(() -> new CustomerNotFoundException());

    List<Phones> phones = phoneRepository.findByCustomersId(customers.getId());

    if (phones != null && !phones.isEmpty()) {
      for (Phones phone : phones) {
        phoneRepository.delete(phone);
      }
    }

    this.customerRepository.delete(customers);
  }

  /**
   * Atualiza os dados de um cliente no sistema.
   *
   * @param id           O ID do cliente a ser atualizado.
   * @param customersDTO Os novos dados do cliente.
   * @return O cliente atualizado.
   * @throws CustomerNotFoundException Se o cliente não for encontrado.
   */
  @Transactional
  public Customers update(Long id, CustomersDTO customersDTO) {
    try {
      Customers customers = customerRepository.findById(id)
          .orElseThrow(() -> new CustomerNotFoundException());

      validatePhones(customersDTO.phones());

      customers.setName(customersDTO.name());
      customers.setAddress(customersDTO.address());
      customers.setDistrict(customersDTO.district());

      List<Phones> existingPhones = phoneRepository.findByCustomersId(id);
      List<String> newPhoneNumbers = customersDTO.phones();

      existingPhones.stream()
          .filter(phone -> !newPhoneNumbers.contains(phone.getNumber()))
          .forEach(phoneRepository::delete);

      for (String newPhoneNumber : newPhoneNumbers) {
        Phones phone = existingPhones.stream()
            .filter(existingPhone -> existingPhone.getNumber().equals(newPhoneNumber))
            .findFirst()
            .orElseGet(() -> new Phones(newPhoneNumber));

        // Verifica se o telefone pertence a outro cliente
        if (phone.getCustomers() != null && !phone.getCustomers().equals(customers)) {
          throw new PhoneAlreadyLinkedException();
        }

        phone.setCustomers(customers);
        phoneRepository.save(phone);
      }

      return customerRepository.save(customers);
    } catch (CustomerNotFoundException | PhoneEmptyException | PhoneFormatInvalidException
        | PhoneAlreadyLinkedException e) {
      throw new RuntimeException("Erro ao atualizar cliente", e);
    }
  }

  /**
   * Obtém um cliente pelo ID.
   *
   * @param id O ID do cliente a ser recuperado.
   * @return O cliente encontrado.
   * @throws CustomerNotFoundException Se o cliente não for encontrado.
   */
  @Transactional
  public Customers getCustomerById(Long id) {
    Customers customers = customerRepository.findById(id)
        .orElseThrow(CustomerNotFoundException::new);

    customers.getPhones();

    return customers;
  }

  /**
   * Verifica se um número de telefone tem um formato válido.
   *
   * @param phoneNumber O número de telefone a ser verificado.
   * @return true se o número de telefone tiver um formato válido, false caso
   *         contrário.
   */
  private boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("\\d{10,11}");
  }

  /**
   * Valida a lista de números de telefone, garantindo que não está vazia, que
   * cada número é válido
   * e que nenhum dos números já está associado a outro cliente.
   *
   * @param phoneNumbers Lista de números de telefone a serem validados.
   * @throws PhoneEmptyException         Se a lista de números de telefone estiver
   *                                     vazia.
   * @throws PhoneFormatInvalidException Se pelo menos um número de telefone não
   *                                     for válido.
   * @throws PhoneAlreadyLinkedException Se pelo menos um número de telefone já
   *                                     estiver associado a outro cliente.
   */
  private void validatePhones(List<String> phoneNumbers) {
    if (phoneNumbers == null || phoneNumbers.isEmpty() || phoneNumbers.size() == 0
        || phoneNumbers.stream().allMatch(String::isEmpty)) {
      throw new PhoneEmptyException();
    }

    for (String phoneNumber : phoneNumbers) {
      if (!isValidPhoneNumber(phoneNumber)) {
        throw new PhoneFormatInvalidException(phoneNumber);
      }

      Phones phoneAlreadyExist = phoneRepository.findByNumber(phoneNumber);
      if (phoneAlreadyExist != null) {
        throw new PhoneAlreadyLinkedException();
      }
    }
  }

  /**
   * Cria entidades de telefone com base nos números fornecidos e associa-as a um
   * cliente.
   *
   * @param customer     O cliente ao qual os telefones serão associados.
   * @param phoneNumbers Lista de números de telefone a serem utilizados para
   *                     criar as entidades de telefone.
   * @return Lista de entidades de telefone associadas ao cliente.
   */
  private List<Phones> createPhoneEntities(Customers customer, List<String> phoneNumbers) {
    return phoneNumbers.stream()
        .map(phoneNumber -> {
          Phones phone = new Phones(phoneNumber);
          phone.setCustomers(customer);
          return phone;
        })
        .collect(Collectors.toList());
  }
}
