package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.PickupDto;
import org.lunifera.samples.carstore.entities.general.Pickup;

@SuppressWarnings("all")
public class PickupService extends AbstractDTOService<PickupDto, Pickup> {
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
