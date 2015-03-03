package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.CustomerDto;
import org.lunifera.samples.carstore.entities.general.Customer;

@SuppressWarnings("all")
public class CustomerService extends AbstractDTOService<CustomerDto, Customer> {
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
