package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.CascadeNoDto;
import org.lunifera.samples.carstore.entities.general.CascadeNo;

@SuppressWarnings("all")
public class CascadeNoService extends AbstractDTOService<CascadeNoDto, CascadeNo> {
  public Class<CascadeNoDto> getDtoClass() {
    return CascadeNoDto.class;
  }
  
  public CascadeNoDto createDto() {
    return new CascadeNoDto();
  }
  
  public CascadeNo createEntity() {
    return new CascadeNo();
  }
  
  public Class<CascadeNo> getEntityClass() {
    return CascadeNo.class;
  }
  
  public Object getId(final CascadeNoDto dto) {
    return dto.getId();
  }
}
