package com.example.totvsapp;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.totvsapp.domain.Customers.Customers;
import com.example.totvsapp.domain.Customers.CustomersDTO;

class TotvsAppApplicationTests {

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
	}
}
