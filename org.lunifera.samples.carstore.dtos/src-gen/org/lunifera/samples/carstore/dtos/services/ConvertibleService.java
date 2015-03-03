package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Convertible;
import org.lunifera.samples.carstore.dtos.ConvertibleDto;

@SuppressWarnings("all")
public class ConvertibleService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<ConvertibleDto> getDtoClass() {
    return ConvertibleDto.class;
  }
  
  public ConvertibleDto createDto() {
    return new ConvertibleDto();
  }
  
  public Convertible createEntity() {
    return new Convertible();
  }
  
  public Class<Convertible> getEntityClass() {
    return Convertible.class;
  }
  
  public Object getId(final ConvertibleDto dto) {
    return dto.getId();
  }
}
