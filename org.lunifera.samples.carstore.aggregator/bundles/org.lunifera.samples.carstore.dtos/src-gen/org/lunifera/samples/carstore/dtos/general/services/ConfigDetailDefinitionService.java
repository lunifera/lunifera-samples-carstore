package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;

@SuppressWarnings("all")
public class ConfigDetailDefinitionService extends AbstractDTOServiceWithMutablePersistence<ConfigDetailDefinitionDto, ConfigDetailDefinition> {
  public ConfigDetailDefinitionService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
