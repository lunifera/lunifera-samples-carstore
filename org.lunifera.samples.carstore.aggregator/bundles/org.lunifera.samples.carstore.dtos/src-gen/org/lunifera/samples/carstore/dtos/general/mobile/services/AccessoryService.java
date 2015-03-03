package org.lunifera.samples.carstore.dtos.general.mobile.services;

import org.lunifera.samples.carstore.dtos.general.mobile.AccessoryMobileDto;
import org.lunifera.samples.carstore.entities.general.Accessory;

@SuppressWarnings("all")
public class AccessoryService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<AccessoryMobileDto> getDtoClass() {
    return AccessoryMobileDto.class;
  }
  
  public AccessoryMobileDto createDto() {
    return new AccessoryMobileDto();
  }
  
  public Accessory createEntity() {
    return new Accessory();
  }
  
  public Class<Accessory> getEntityClass() {
    return Accessory.class;
  }
  
  public Object getId(final AccessoryMobileDto dto) {
    throw new UnsupportedOperationException("No id available for DTO.");
  }
}
