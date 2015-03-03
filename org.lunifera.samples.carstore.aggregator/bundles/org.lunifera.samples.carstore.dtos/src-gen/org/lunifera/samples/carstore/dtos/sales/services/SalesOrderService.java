package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDto;
import org.lunifera.samples.carstore.entities.sales.SalesOrder;

@SuppressWarnings("all")
public class SalesOrderService extends AbstractDTOService<SalesOrderDto, SalesOrder> {
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
