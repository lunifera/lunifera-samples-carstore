package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.CustomerDto;
import org.lunifera.samples.carstore.entities.general.Customer;

@SuppressWarnings("all")
public class CustomerDtoService extends AbstractDTOServiceWithMutablePersistence<CustomerDto, Customer> {
  public CustomerDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
