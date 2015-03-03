package org.lunifera.samples.carstore.dtos.general.mobile.services;

import org.lunifera.samples.carstore.dtos.general.mobile.CarMobileDto;
import org.lunifera.samples.carstore.entities.general.Car;

@SuppressWarnings("all")
public class CarService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<CarMobileDto> getDtoClass() {
    return CarMobileDto.class;
  }
  
  public CarMobileDto createDto() {
    return new CarMobileDto();
  }
  
  public Car createEntity() {
    return new Car();
  }
  
  public Class<Car> getEntityClass() {
    return Car.class;
  }
  
  public Object getId(final CarMobileDto dto) {
    throw new UnsupportedOperationException("No id available for DTO.");
  }
}
