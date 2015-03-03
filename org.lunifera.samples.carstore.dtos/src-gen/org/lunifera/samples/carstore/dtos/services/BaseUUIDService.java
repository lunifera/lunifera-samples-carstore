package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;

@SuppressWarnings("all")
public class BaseUUIDService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<BaseUUIDDto> getDtoClass() {
    return BaseUUIDDto.class;
  }
  
  public BaseUUIDDto createDto() {
    return new BaseUUIDDto();
  }
  
  public BaseUUID createEntity() {
    return new BaseUUID();
  }
  
  public Class<BaseUUID> getEntityClass() {
    return BaseUUID.class;
  }
  
  public Object getId(final BaseUUIDDto dto) {
    return dto.getId();
  }
}
