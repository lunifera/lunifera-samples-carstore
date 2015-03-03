package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Customer;
import org.lunifera.samples.carstore.dtos.CustomerDto;

@SuppressWarnings("all")
public class CustomerService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<CustomerDto> getDtoClass() {
    return CustomerDto.class;
  }
  
  public CustomerDto createDto() {
    return new CustomerDto();
  }
  
  public Customer createEntity() {
    return new Customer();
  }
  
  public Class<Customer> getEntityClass() {
    return Customer.class;
  }
  
  public Object getId(final CustomerDto dto) {
    return dto.getId();
  }
}
