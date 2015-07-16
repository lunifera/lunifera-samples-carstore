package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.ItemDto;
import org.lunifera.samples.carstore.dtos.general.mapper.NumberedWithDescriptionDtoMapper;
import org.lunifera.samples.carstore.entities.general.Item;

/**
 * This class maps the dto {@link ItemDto} to and from the entity {@link Item}.
 * 
 */
@SuppressWarnings("all")
public class ItemDtoMapper<DTO extends ItemDto, ENTITY extends Item> extends NumberedWithDescriptionDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Item createEntity() {
    throw new UnsupportedOperationException("Subclass needs to provide dto.");
  }
  
  /**
   * Creates a new instance of the dto
   */
  public ItemDto createDto() {
    throw new UnsupportedOperationException("Subclass needs to provide dto.");
  }
  
  /**
   * Maps the entity {@link Item} to the dto {@link ItemDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final ItemDto dto, final Item entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
  }
  
  /**
   * Maps the dto {@link ItemDto} to the entity {@link Item}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final ItemDto dto, final Item entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ItemDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Item.class, in);
  }
}
