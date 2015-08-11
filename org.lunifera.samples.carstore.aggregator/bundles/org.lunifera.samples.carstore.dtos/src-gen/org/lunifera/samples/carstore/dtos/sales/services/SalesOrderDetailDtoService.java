package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

@SuppressWarnings("all")
public class SalesOrderDetailDtoService extends AbstractDTOServiceWithMutablePersistence<SalesOrderDetailDto, SalesOrderDetail> {
  public SalesOrderDetailDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
