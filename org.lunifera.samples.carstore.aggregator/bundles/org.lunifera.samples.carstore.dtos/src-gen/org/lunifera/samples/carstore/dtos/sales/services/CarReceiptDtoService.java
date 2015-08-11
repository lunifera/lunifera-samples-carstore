package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.sales.CarReceiptDto;
import org.lunifera.samples.carstore.entities.sales.CarReceipt;

@SuppressWarnings("all")
public class CarReceiptDtoService extends AbstractDTOServiceWithMutablePersistence<CarReceiptDto, CarReceipt> {
  public CarReceiptDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<CarReceiptDto> getDtoClass() {
    return CarReceiptDto.class;
  }
  
  public CarReceiptDto createDto() {
    return new CarReceiptDto();
  }
  
  public CarReceipt createEntity() {
    return new CarReceipt();
  }
  
  public Class<CarReceipt> getEntityClass() {
    return CarReceipt.class;
  }
  
  public Object getId(final CarReceiptDto dto) {
    return dto.getId();
  }
}
