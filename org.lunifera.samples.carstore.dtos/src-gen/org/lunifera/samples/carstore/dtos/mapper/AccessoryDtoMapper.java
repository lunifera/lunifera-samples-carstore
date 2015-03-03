package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Accessory;
import org.lunifera.samples.carstore.dtos.AccessoryDto;
import org.lunifera.samples.carstore.dtos.mapper.ItemDtoMapper;

/**
 * This class maps the dto {@link AccessoryDto} to and from the entity {@link Accessory}.
 * 
 */
@SuppressWarnings("all")
public class AccessoryDtoMapper<DTO extends AccessoryDto, ENTITY extends Accessory> extends ItemDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Accessory} to the dto {@link AccessoryDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final AccessoryDto dto, final Accessory entity) {
    super.mapToDTO(dto, entity);
    
    
  }
  
  /**
   * Maps the dto {@link AccessoryDto} to the entity {@link Accessory}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final AccessoryDto dto, final Accessory entity) {
    super.mapToEntity(dto, entity);
    
    
  }
}
