package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.UnitOfMeasure;
import org.lunifera.samples.carstore.dtos.UnitOfMeasureDto;

@SuppressWarnings("all")
public class UnitOfMeasureService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<UnitOfMeasureDto> getDtoClass() {
    return UnitOfMeasureDto.class;
  }
  
  public UnitOfMeasureDto createDto() {
    return new UnitOfMeasureDto();
  }
  
  public UnitOfMeasure createEntity() {
    return new UnitOfMeasure();
  }
  
  public Class<UnitOfMeasure> getEntityClass() {
    return UnitOfMeasure.class;
  }
  
  public Object getId(final UnitOfMeasureDto dto) {
    return dto.getId();
  }
}
