package com.example.totvsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.totvsapp.domain.Customers.Customers;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Long> {

}
