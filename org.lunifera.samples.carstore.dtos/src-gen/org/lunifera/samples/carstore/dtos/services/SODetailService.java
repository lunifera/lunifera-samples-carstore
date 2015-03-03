package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.SODetail;
import org.lunifera.samples.carstore.dtos.SODetailDto;

@SuppressWarnings("all")
public class SODetailService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<SODetailDto> getDtoClass() {
    return SODetailDto.class;
  }
  
  public SODetailDto createDto() {
    return new SODetailDto();
  }
  
  public SODetail createEntity() {
    return new SODetail();
  }
  
  public Class<SODetail> getEntityClass() {
    return SODetail.class;
  }
  
  public Object getId(final SODetailDto dto) {
    return dto.getId();
  }
}
