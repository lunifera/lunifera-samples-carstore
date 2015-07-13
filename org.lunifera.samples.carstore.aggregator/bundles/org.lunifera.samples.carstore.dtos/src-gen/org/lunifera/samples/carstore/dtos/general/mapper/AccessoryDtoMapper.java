package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.AccessoryDto;
import org.lunifera.samples.carstore.dtos.general.mapper.ItemDtoMapper;
import org.lunifera.samples.carstore.entities.general.Accessory;

/**
 * This class maps the dto {@link AccessoryDto} to and from the entity {@link Accessory}.
 * 
 */
@SuppressWarnings("all")
public class AccessoryDtoMapper<DTO extends AccessoryDto, ENTITY extends Accessory> extends ItemDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Accessory createEntity() {
    return new Accessory();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public AccessoryDto createDto() {
    return new AccessoryDto();
  }
  
  /**
   * Maps the entity {@link Accessory} to the dto {@link AccessoryDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final AccessoryDto dto, final Accessory entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
  }
  
  /**
   * Maps the dto {@link AccessoryDto} to the entity {@link Accessory}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final AccessoryDto dto, final Accessory entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(AccessoryDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Accessory.class, in);
  }
}
