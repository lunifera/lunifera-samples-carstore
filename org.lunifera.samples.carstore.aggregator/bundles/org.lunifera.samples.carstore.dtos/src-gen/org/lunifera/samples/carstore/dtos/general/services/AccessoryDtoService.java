package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.AccessoryDto;
import org.lunifera.samples.carstore.entities.general.Accessory;

@SuppressWarnings("all")
public class AccessoryDtoService extends AbstractDTOServiceWithMutablePersistence<AccessoryDto, Accessory> {
  public AccessoryDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<AccessoryDto> getDtoClass() {
    return AccessoryDto.class;
  }
  
  public AccessoryDto createDto() {
    return new AccessoryDto();
  }
  
  public Accessory createEntity() {
    return new Accessory();
  }
  
  public Class<Accessory> getEntityClass() {
    return Accessory.class;
  }
  
  public Object getId(final AccessoryDto dto) {
    return dto.getId();
  }
}
