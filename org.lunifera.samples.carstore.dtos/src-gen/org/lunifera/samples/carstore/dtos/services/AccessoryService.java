package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Accessory;
import org.lunifera.samples.carstore.dtos.AccessoryDto;

@SuppressWarnings("all")
public class AccessoryService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
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
