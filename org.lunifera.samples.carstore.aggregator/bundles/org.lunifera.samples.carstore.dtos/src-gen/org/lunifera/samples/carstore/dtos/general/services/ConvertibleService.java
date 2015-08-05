package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.ConvertibleDto;
import org.lunifera.samples.carstore.entities.general.Convertible;

@SuppressWarnings("all")
public class ConvertibleService extends AbstractDTOServiceWithMutablePersistence<ConvertibleDto, Convertible> {
  public ConvertibleService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
