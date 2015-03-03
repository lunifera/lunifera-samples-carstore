package org.lunifera.samples.carstore.dtos.general.mobile.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.mobile.ConvertibleMobileDto;
import org.lunifera.samples.carstore.entities.general.Convertible;

@SuppressWarnings("all")
public class ConvertibleService extends AbstractDTOService<ConvertibleMobileDto, Convertible> {
  public Class<ConvertibleMobileDto> getDtoClass() {
    return ConvertibleMobileDto.class;
  }
  
  public ConvertibleMobileDto createDto() {
    return new ConvertibleMobileDto();
  }
  
  public Convertible createEntity() {
    return new Convertible();
  }
  
  public Class<Convertible> getEntityClass() {
    return Convertible.class;
  }
  
  public Object getId(final ConvertibleMobileDto dto) {
    return dto.getId();
  }
}
