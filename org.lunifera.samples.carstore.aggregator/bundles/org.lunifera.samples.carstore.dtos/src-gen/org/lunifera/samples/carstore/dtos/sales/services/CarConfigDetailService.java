package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.sales.CarConfigDetailDto;
import org.lunifera.samples.carstore.entities.sales.CarConfigDetail;

@SuppressWarnings("all")
public class CarConfigDetailService extends AbstractDTOServiceWithMutablePersistence<CarConfigDetailDto, CarConfigDetail> {
  public CarConfigDetailService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<CarConfigDetailDto> getDtoClass() {
    return CarConfigDetailDto.class;
  }
  
  public CarConfigDetailDto createDto() {
    return new CarConfigDetailDto();
  }
  
  public CarConfigDetail createEntity() {
    return new CarConfigDetail();
  }
  
  public Class<CarConfigDetail> getEntityClass() {
    return CarConfigDetail.class;
  }
  
  public Object getId(final CarConfigDetailDto dto) {
    return dto.getId();
  }
}
