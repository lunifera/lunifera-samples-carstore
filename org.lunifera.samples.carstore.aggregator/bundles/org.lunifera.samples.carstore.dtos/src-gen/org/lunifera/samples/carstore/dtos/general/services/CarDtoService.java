package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.entities.general.Car;

@SuppressWarnings("all")
public class CarDtoService extends AbstractDTOServiceWithMutablePersistence<CarDto, Car> {
  public CarDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
