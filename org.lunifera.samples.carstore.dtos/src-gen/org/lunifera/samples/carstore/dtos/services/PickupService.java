package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Pickup;
import org.lunifera.samples.carstore.dtos.PickupDto;

@SuppressWarnings("all")
public class PickupService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<PickupDto> getDtoClass() {
    return PickupDto.class;
  }
  
  public PickupDto createDto() {
    return new PickupDto();
  }
  
  public Pickup createEntity() {
    return new Pickup();
  }
  
  public Class<Pickup> getEntityClass() {
    return Pickup.class;
  }
  
  public Object getId(final PickupDto dto) {
    return dto.getId();
  }
}
