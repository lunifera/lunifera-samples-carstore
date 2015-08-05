package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.sales.ManufacturerOrderDto;
import org.lunifera.samples.carstore.entities.sales.ManufacturerOrder;

@SuppressWarnings("all")
public class ManufacturerOrderService extends AbstractDTOServiceWithMutablePersistence<ManufacturerOrderDto, ManufacturerOrder> {
  public ManufacturerOrderService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<ManufacturerOrderDto> getDtoClass() {
    return ManufacturerOrderDto.class;
  }
  
  public ManufacturerOrderDto createDto() {
    return new ManufacturerOrderDto();
  }
  
  public ManufacturerOrder createEntity() {
    return new ManufacturerOrder();
  }
  
  public Class<ManufacturerOrder> getEntityClass() {
    return ManufacturerOrder.class;
  }
  
  public Object getId(final ManufacturerOrderDto dto) {
    return dto.getId();
  }
}
