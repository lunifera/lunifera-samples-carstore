package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;

@SuppressWarnings("all")
public class ConfigDetailDefinitionService extends AbstractDTOService<ConfigDetailDefinitionDto, ConfigDetailDefinition> {
  public Class<ConfigDetailDefinitionDto> getDtoClass() {
    return ConfigDetailDefinitionDto.class;
  }
  
  public ConfigDetailDefinitionDto createDto() {
    return new ConfigDetailDefinitionDto();
  }
  
  public ConfigDetailDefinition createEntity() {
    return new ConfigDetailDefinition();
  }
  
  public Class<ConfigDetailDefinition> getEntityClass() {
    return ConfigDetailDefinition.class;
  }
  
  public Object getId(final ConfigDetailDefinitionDto dto) {
    return dto.getId();
  }
}
