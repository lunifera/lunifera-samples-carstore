package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

@SuppressWarnings("all")
public class SalesOrderDetailService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<SalesOrderDetailDto> getDtoClass() {
    return SalesOrderDetailDto.class;
  }
  
  public SalesOrderDetailDto createDto() {
    return new SalesOrderDetailDto();
  }
  
  public SalesOrderDetail createEntity() {
    return new SalesOrderDetail();
  }
  
  public Class<SalesOrderDetail> getEntityClass() {
    return SalesOrderDetail.class;
  }
  
  public Object getId(final SalesOrderDetailDto dto) {
    return dto.getId();
  }
}
