package com.example.totvsapp.domain.Customers;

import java.util.List;

public record CustomersDTO(String name, String address, String district, List<String> phones) {
}