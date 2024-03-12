package com.example.totvsapp.domain.Customers;

import java.util.List;
import java.util.stream.Collectors;

import com.example.totvsapp.domain.Phones.Phones;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "customer")
@Entity(name = "customer")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customers {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String address;

  private String district;

  @OneToMany(mappedBy = "customers", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Phones> phones;

  public Customers(CustomersDTO customersDTO) {
    this.name = customersDTO.name();
    this.address = customersDTO.address();
    this.district = customersDTO.district();
    this.phones = createPhoneEntities(customersDTO.phones());
  }

  private List<Phones> createPhoneEntities(List<String> phoneNumbers) {
    return phoneNumbers.stream()
        .map(phoneNumber -> {
          Phones phone = new Phones(phoneNumber);
          phone.setCustomers(this);
          return phone;
        })
        .collect(Collectors.toList());
  }
}
