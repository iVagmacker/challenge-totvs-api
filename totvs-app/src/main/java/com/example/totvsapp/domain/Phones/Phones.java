package com.example.totvsapp.domain.Phones;

import com.example.totvsapp.domain.Customers.Customers;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "phones")
@Table(name = "phones")
@Getter
@Setter
public class Phones {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String number;

  @ManyToOne(optional = false)
  @JoinColumn(name = "customer_id")
  @JsonIgnore
  private Customers customers;

  public Phones() {
    
  }

  public Phones(String number) {
    this.number = number;
  }
}
