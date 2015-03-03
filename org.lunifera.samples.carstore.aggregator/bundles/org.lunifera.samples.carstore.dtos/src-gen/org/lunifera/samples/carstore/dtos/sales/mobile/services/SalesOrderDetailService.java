package org.lunifera.samples.carstore.dtos.sales.mobile.services;

import org.lunifera.samples.carstore.dtos.sales.mobile.SalesOrderDetailMobileDto;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

@SuppressWarnings("all")
public class SalesOrderDetailService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<SalesOrderDetailMobileDto> getDtoClass() {
    return SalesOrderDetailMobileDto.class;
  }
  
  public SalesOrderDetailMobileDto createDto() {
    return new SalesOrderDetailMobileDto();
  }
  
  public SalesOrderDetail createEntity() {
    return new SalesOrderDetail();
  }
  
  public Class<SalesOrderDetail> getEntityClass() {
    return SalesOrderDetail.class;
  }
  
  public Object getId(final SalesOrderDetailMobileDto dto) {
    return dto.getId();
  }
}
