package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.UnitOfMeasureDto;
import org.lunifera.samples.carstore.entities.general.UnitOfMeasure;

@SuppressWarnings("all")
public class UnitOfMeasureDtoService extends AbstractDTOServiceWithMutablePersistence<UnitOfMeasureDto, UnitOfMeasure> {
  public UnitOfMeasureDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
