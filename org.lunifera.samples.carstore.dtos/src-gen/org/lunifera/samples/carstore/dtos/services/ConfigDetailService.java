package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.ConfigDetail;
import org.lunifera.samples.carstore.dtos.ConfigDetailDto;

@SuppressWarnings("all")
public class ConfigDetailService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<ConfigDetailDto> getDtoClass() {
    return ConfigDetailDto.class;
  }
  
  public ConfigDetailDto createDto() {
    return new ConfigDetailDto();
  }
  
  public ConfigDetail createEntity() {
    return new ConfigDetail();
  }
  
  public Class<ConfigDetail> getEntityClass() {
    return ConfigDetail.class;
  }
  
  public Object getId(final ConfigDetailDto dto) {
    return dto.getId();
  }
}
