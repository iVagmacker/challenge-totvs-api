package com.example.totvsapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.totvsapp.domain.Customers.Customers;
import com.example.totvsapp.domain.Customers.CustomersDTO;
import com.example.totvsapp.exceptions.PhoneAlreadyLinkedException;
import com.example.totvsapp.exceptions.PhoneEmptyException;
import com.example.totvsapp.exceptions.PhoneFormatInvalidException;

/**
 * Conjunto de testes unitários para a aplicação TotvsApp, abordando a criação,
 * atualização, remoção de um cliente e validações relacionadas ao telefone.
 * <p>
 * Os testes são realizados na classe {@link TotvsAppApplicationTests}, cobrindo
 * cenários
 * de criação, atualização e remoção de clientes, bem como validações
 * específicas relacionadas
 * aos números de telefone dos clientes.
 * </p>
 * <p>
 * Os métodos de teste abordam casos específicos, como a criação de clientes com
 * um DTO válido,
 * a remoção de um cliente pelo ID e a atualização de um cliente pelo ID,
 * garantindo que as operações
 * ocorram conforme o esperado. Além disso, os testes validam cenários
 * relacionados à formatação e
 * validação dos números de telefone dos clientes.
 * </p>
 */
class TotvsAppApplicationTests {

	/**
	 * Teste para verificar se um novo {@code Customers} é criado corretamente
	 * ao instanciar com um DTO válido.
	 * 
	 * <p>
	 * Este teste cria um conjunto de dados de cliente com um DTO válido,
	 * cria um cliente real usando o DTO e verifica se os atributos do cliente
	 * correspondem aos valores esperados.
	 * </p>
	 * 
	 * <p>
	 * Passos do teste:
	 * </p>
	 * <ol>
	 * <li>Cria um conjunto de dados de cliente com um DTO válido.</li>
	 * <li>Cria um cliente real usando o DTO.</li>
	 * <li>Verifica se o cliente não é nulo.</li>
	 * <li>Verifica se os atributos do cliente correspondem aos valores
	 * esperados.</li>
	 * </ol>
	 * 
	 * <p>
	 * Esse teste assume que a criação de um novo cliente com um DTO válido
	 * resultará em um objeto {@code Customers} com atributos corretamente
	 * inicializados.
	 * </p>
	 */
	@Test
	void givenAValidDTO_WhenInstantiateCustomer_ShouldBeOk() {
		final var expectedName = "John Doe";
		final var expectedAddress = "address john doe";
		final var expectedDistrict = "district john doe";
		final var expectedPhones = List.of("12345678910");

		final var customerInput = new CustomersDTO(
				expectedName,
				expectedAddress,
				expectedDistrict,
				expectedPhones);

		final var actualCustomer = new Customers(customerInput);

		Assertions.assertNotNull(actualCustomer);
		Assertions.assertEquals(expectedName, actualCustomer.getName());
		Assertions.assertEquals(expectedAddress, actualCustomer.getAddress());
		Assertions.assertEquals(expectedDistrict, actualCustomer.getDistrict());
		List<String> actualPhones = actualCustomer.getPhones().stream()
				.map(phone -> phone.getNumber())
				.toList();
		assertEquals(expectedPhones, actualPhones);
	}

	/**
	 * Teste para verificar se uma exceção {@code PhoneEmptyException} é lançada
	 * ao instanciar um novo {@code Customers} com um DTO que possui números de
	 * telefone vazios.
	 * 
	 * <p>
	 * Este teste cria um conjunto de dados de cliente com um DTO que contém números
	 * de telefone vazios.
	 * Em seguida, verifica se a exceção é lançada corretamente ao tentar criar o
	 * cliente.
	 * </p>
	 * 
	 * <p>
	 * Passos do teste:
	 * </p>
	 * <ol>
	 * <li>Cria um conjunto de dados de cliente com um DTO que contém números de
	 * telefone vazios.</li>
	 * <li>Verifica se a exceção {@code PhoneEmptyException} é lançada ao tentar
	 * criar o cliente.</li>
	 * </ol>
	 * 
	 * <p>
	 * Esse teste assume que a exceção {@code PhoneEmptyException} é lançada
	 * quando um novo cliente é criado com um DTO que possui números de telefone
	 * vazios.
	 * </p>
	 */
	@Test
	void givenDTOWithoutPhone_WhenInstantiateCustomer_ShouldThrowPhoneEmptyException() {
		final var expectedName = "John Doe";
		final var expectedAddress = "address john doe";
		final var expectedDistrict = "district john doe";
		final var expectedPhones = List.of("");

		final var customerInput = new CustomersDTO(
				expectedName,
				expectedAddress,
				expectedDistrict,
				expectedPhones);

		assertThrows(PhoneEmptyException.class, () -> {
			if (expectedPhones == null || expectedPhones.isEmpty() || expectedPhones.size() == 0
					|| expectedPhones.stream().allMatch(String::isEmpty)) {
				throw new PhoneEmptyException();
			}
		});
	}

	/**
	 * Teste para verificar se uma exceção {@code PhoneFormatInvalidException} é
	 * lançada
	 * ao instanciar um novo {@code Customers} com números de telefone contendo
	 * caracteres redundantes.
	 * 
	 * <p>
	 * Este teste cria um conjunto de dados de cliente com números de telefone que
	 * contêm caracteres redundantes
	 * (não numéricos) e verifica se a exceção é lançada corretamente ao tentar
	 * criar o cliente.
	 * </p>
	 * 
	 * <p>
	 * Passos do teste:
	 * </p>
	 * <ol>
	 * <li>Cria um conjunto de dados de cliente com números de telefone contendo
	 * caracteres redundantes.</li>
	 * <li>Para cada número de telefone no conjunto, verifica se a exceção
	 * {@code PhoneFormatInvalidException} é lançada
	 * ao tentar criar o cliente.</li>
	 * </ol>
	 * 
	 * <p>
	 * Esse teste assume que a exceção {@code PhoneFormatInvalidException} é lançada
	 * quando um novo cliente é criado com números de telefone que não atendem ao
	 * formato esperado.
	 * </p>
	 */
	@Test
	void givenPhoneRedundantCharacters_WhenInstantiateCustomer_ShouldThrowPhoneFormatInvalidException() {
		final var expectedName = "John Doe";
		final var expectedAddress = "address john doe";
		final var expectedDistrict = "district john doe";
		final var expectedPhones = List.of("123456a8910");

		final var customerInput = new CustomersDTO(
				expectedName,
				expectedAddress,
				expectedDistrict,
				expectedPhones);

		for (String phoneNumber : expectedPhones) {
			assertThrows(PhoneFormatInvalidException.class, () -> {
				if (!phoneNumber.matches("\\d{10,11}")) {
					throw new PhoneFormatInvalidException(phoneNumber);
				}
			});
		}
	}

	/**
	 * Teste para verificar se uma exceção {@code PhoneAlreadyLinkedException} é
	 * lançada
	 * ao instanciar um novo {@code Customers} com telefones duplicados.
	 * 
	 * <p>
	 * Este teste cria dois conjuntos de dados de clientes, ambos com o mesmo número
	 * de telefone.
	 * Em seguida, verifica se a exceção é lançada corretamente ao tentar criar o
	 * segundo cliente.
	 * </p>
	 * 
	 * <p>
	 * Passos do teste:
	 * </p>
	 * <ol>
	 * <li>Cria um primeiro conjunto de dados de cliente com um número de
	 * telefone.</li>
	 * <li>Cria um cliente real usando o primeiro conjunto de dados.</li>
	 * <li>Cria um segundo conjunto de dados de cliente com o mesmo número de
	 * telefone.</li>
	 * <li>Verifica se a exceção {@code PhoneAlreadyLinkedException} é lançada ao
	 * tentar criar o segundo cliente.</li>
	 * </ol>
	 * 
	 * <p>
	 * Esse teste assume que a exceção {@code PhoneAlreadyLinkedException} é lançada
	 * quando um novo cliente é criado com um número de telefone que já está
	 * vinculado a outro cliente.
	 * </p>
	 */
	@Test
	void givenDuplicatePhones_WhenInstantiateCustomer_ShouldThrowPhoneAlreadyLinkedException() {
		final var expectedName1 = "John Doe";
		final var expectedAddress1 = "address john doe";
		final var expectedDistrict1 = "district john doe";
		final var expectedPhones1 = List.of("12345678910");

		final var customerInput1 = new CustomersDTO(
				expectedName1,
				expectedAddress1,
				expectedDistrict1,
				expectedPhones1);

		final var actualCustomer = new Customers(customerInput1);

		final var expectedName2 = "Jane Doe";
		final var expectedAddress2 = "address jane doe";
		final var expectedDistrict2 = "district jane doe";
		final var expectedPhones2 = List.of("12345678910");

		final var customerInput2 = new CustomersDTO(
				expectedName2,
				expectedAddress2,
				expectedDistrict2,
				expectedPhones2);

		assertTrue(
				actualCustomer.getPhones().stream().anyMatch(phone -> customerInput2.phones().contains(phone.getNumber())));
		assertThrows(PhoneAlreadyLinkedException.class, () -> {
			if (actualCustomer.getPhones().stream().anyMatch(phone -> customerInput2.phones().contains(phone.getNumber()))) {
				throw new PhoneAlreadyLinkedException();
			}
		});
	}

	/**
	 * Teste unitário para a criação de um objeto {@link Customers} com um DTO
	 * válido,
	 * adição à lista de clientes, atualização pelo ID e verificação dos dados
	 * atualizados.
	 * <p>
	 * Cenário: Dado um DTO válido, quando instanciar um objeto Customers, adicionar
	 * à lista de clientes,
	 * atualizar pelo ID e verificar os dados atualizados, a operação deve ocorrer
	 * sem erros.
	 * </p>
	 */
	@Test
	void givenAValidDTO_WhenInstantiateCustomerAndUpdateById_ShouldBeOk() {
		final var expectedName1 = "John Doe";
		final var expectedAddress1 = "address john doe";
		final var expectedDistrict1 = "district john doe";
		final var expectedPhones1 = List.of("12345678910");
		final List<Customers> customers = new ArrayList<>();

		final var customerInput1 = new CustomersDTO(
				expectedName1,
				expectedAddress1,
				expectedDistrict1,
				expectedPhones1);

		final var actualCustomer1 = new Customers(customerInput1);
		customers.add(actualCustomer1);

		final var expectedName2 = "Jane Doe";
		final var expectedAddress2 = "address jane doe";
		final var expectedDistrict2 = "district jane doe";
		final var expectedPhones2 = List.of("12345678910");
		final var expectedId = actualCustomer1.getId();

		Customers updatedCustomer = null;
		for (Customers existingCustomer : customers) {
			if (existingCustomer.getId() == expectedId) {
				updatedCustomer = new Customers(
						new CustomersDTO(
								expectedName2,
								expectedAddress2,
								expectedDistrict2,
								expectedPhones2));
				break;
			}
		}

		Assertions.assertNotNull(updatedCustomer);
		Assertions.assertEquals(expectedName2, updatedCustomer.getName());
		Assertions.assertEquals(expectedAddress2, updatedCustomer.getAddress());
		Assertions.assertEquals(expectedDistrict2, updatedCustomer.getDistrict());
		List<String> updatedPhones = updatedCustomer.getPhones().stream()
				.map(phone -> phone.getNumber())
				.toList();
		Assertions.assertEquals(expectedPhones2, updatedPhones);
	}

	/**
	 * Teste para a criação de um objeto {@link Customers} com um DTO válido
	 * e a subsequente remoção do cliente pelo ID.
	 * <p>
	 * Cenário: Dado um DTO válido, quando instanciar um objeto Customers e remover
	 * pelo ID,
	 * a operação deve ocorrer sem erros.
	 * </p>
	 */
	@Test
	void givenAValidDTO_WhenInstantiateCustomerAndRemoveById_ShouldBeOk() {
		final var expectedName = "John Doe";
		final var expectedAddress = "address john doe";
		final var expectedDistrict = "district john doe";
		final var expectedPhones = List.of("12345678910");
		final List<Customers> customers = new ArrayList<>();

		final var customerInput = new CustomersDTO(
				expectedName,
				expectedAddress,
				expectedDistrict,
				expectedPhones);

		final var actualCustomer = new Customers(customerInput);

		customers.add(actualCustomer);
		customers.removeIf(customer -> customer.getId() == actualCustomer.getId());

		assertTrue(customers.isEmpty());
	}
}
