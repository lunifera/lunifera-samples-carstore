package org.lunifera.samples.carstore.dtos.general.mobile.services;

import org.lunifera.samples.carstore.dtos.general.mobile.PickupMobileDto;
import org.lunifera.samples.carstore.entities.general.Pickup;

@SuppressWarnings("all")
public class PickupService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<PickupMobileDto> getDtoClass() {
    return PickupMobileDto.class;
  }
  
  public PickupMobileDto createDto() {
    return new PickupMobileDto();
  }
  
  public Pickup createEntity() {
    return new Pickup();
  }
  
  public Class<Pickup> getEntityClass() {
    return Pickup.class;
  }
  
  public Object getId(final PickupMobileDto dto) {
    throw new UnsupportedOperationException("No id available for DTO.");
  }
}
