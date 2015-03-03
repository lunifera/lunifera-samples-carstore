package org.lunifera.samples.carstore.dtos.general.mobile.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.mobile.AccessoryMobileDto;
import org.lunifera.samples.carstore.entities.general.Accessory;

@SuppressWarnings("all")
public class AccessoryService extends AbstractDTOService<AccessoryMobileDto, Accessory> {
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
    return dto.getId();
  }
}
