package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDto;
import org.lunifera.samples.carstore.entities.sales.SalesOrder;

@SuppressWarnings("all")
public class SalesOrderDtoService extends AbstractDTOServiceWithMutablePersistence<SalesOrderDto, SalesOrder> {
  public SalesOrderDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<SalesOrderDto> getDtoClass() {
    return SalesOrderDto.class;
  }
  
  public SalesOrderDto createDto() {
    return new SalesOrderDto();
  }
  
  public SalesOrder createEntity() {
    return new SalesOrder();
  }
  
  public Class<SalesOrder> getEntityClass() {
    return SalesOrder.class;
  }
  
  public Object getId(final SalesOrderDto dto) {
    return dto.getId();
  }
}
