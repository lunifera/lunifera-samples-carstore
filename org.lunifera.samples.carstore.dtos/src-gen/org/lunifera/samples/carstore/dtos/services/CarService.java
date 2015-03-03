package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Car;
import org.lunifera.samples.carstore.dtos.CarDto;

@SuppressWarnings("all")
public class CarService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<CarDto> getDtoClass() {
    return CarDto.class;
  }
  
  public CarDto createDto() {
    return new CarDto();
  }
  
  public Car createEntity() {
    return new Car();
  }
  
  public Class<Car> getEntityClass() {
    return Car.class;
  }
  
  public Object getId(final CarDto dto) {
    return dto.getId();
  }
}
