package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.CarManufacturerDto;
import org.lunifera.samples.carstore.entities.general.CarManufacturer;

@SuppressWarnings("all")
public class CarManufacturerService extends AbstractDTOServiceWithMutablePersistence<CarManufacturerDto, CarManufacturer> {
  public CarManufacturerService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<CarManufacturerDto> getDtoClass() {
    return CarManufacturerDto.class;
  }
  
  public CarManufacturerDto createDto() {
    return new CarManufacturerDto();
  }
  
  public CarManufacturer createEntity() {
    return new CarManufacturer();
  }
  
  public Class<CarManufacturer> getEntityClass() {
    return CarManufacturer.class;
  }
  
  public Object getId(final CarManufacturerDto dto) {
    return dto.getId();
  }
}
