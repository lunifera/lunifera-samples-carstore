package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.CascadeYesDto;
import org.lunifera.samples.carstore.entities.general.CascadeYes;

@SuppressWarnings("all")
public class CascadeYesService extends AbstractDTOService<CascadeYesDto, CascadeYes> {
  public Class<CascadeYesDto> getDtoClass() {
    return CascadeYesDto.class;
  }
  
  public CascadeYesDto createDto() {
    return new CascadeYesDto();
  }
  
  public CascadeYes createEntity() {
    return new CascadeYes();
  }
  
  public Class<CascadeYes> getEntityClass() {
    return CascadeYes.class;
  }
  
  public Object getId(final CascadeYesDto dto) {
    return dto.getId();
  }
}
