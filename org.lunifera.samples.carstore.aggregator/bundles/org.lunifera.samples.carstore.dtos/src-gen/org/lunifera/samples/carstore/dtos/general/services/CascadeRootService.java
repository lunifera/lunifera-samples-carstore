package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.CascadeRootDto;
import org.lunifera.samples.carstore.entities.general.CascadeRoot;

@SuppressWarnings("all")
public class CascadeRootService extends AbstractDTOService<CascadeRootDto, CascadeRoot> {
  public Class<CascadeRootDto> getDtoClass() {
    return CascadeRootDto.class;
  }
  
  public CascadeRootDto createDto() {
    return new CascadeRootDto();
  }
  
  public CascadeRoot createEntity() {
    return new CascadeRoot();
  }
  
  public Class<CascadeRoot> getEntityClass() {
    return CascadeRoot.class;
  }
  
  public Object getId(final CascadeRootDto dto) {
    return dto.getId();
  }
}
