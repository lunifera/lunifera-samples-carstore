package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.BaseDto;
import org.lunifera.samples.carstore.entities.general.Base;

@SuppressWarnings("all")
public class BaseDtoService extends AbstractDTOServiceWithMutablePersistence<BaseDto, Base> {
  public BaseDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<BaseDto> getDtoClass() {
    return BaseDto.class;
  }
  
  public BaseDto createDto() {
    return new BaseDto();
  }
  
  public Base createEntity() {
    return new Base();
  }
  
  public Class<Base> getEntityClass() {
    return Base.class;
  }
  
  public Object getId(final BaseDto dto) {
    return dto.getId();
  }
}
