package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.NumberedWithDescriptionDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.entities.general.NumberedWithDescription;

/**
 * This class maps the dto {@link NumberedWithDescriptionDto} to and from the entity {@link NumberedWithDescription}.
 * 
 */
@SuppressWarnings("all")
public class NumberedWithDescriptionDtoMapper<DTO extends NumberedWithDescriptionDto, ENTITY extends NumberedWithDescription> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public NumberedWithDescription createEntity() {
    throw new UnsupportedOperationException("Subclass needs to provide dto.");
  }
  
  /**
   * Creates a new instance of the dto
   */
  public NumberedWithDescriptionDto createDto() {
    throw new UnsupportedOperationException("Subclass needs to provide dto.");
  }
  
  /**
   * Maps the entity {@link NumberedWithDescription} to the dto {@link NumberedWithDescriptionDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final NumberedWithDescriptionDto dto, final NumberedWithDescription entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setDescription(toDto_description(entity, context));
  }
  
  /**
   * Maps the dto {@link NumberedWithDescriptionDto} to the entity {@link NumberedWithDescription}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final NumberedWithDescriptionDto dto, final NumberedWithDescription entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setNumber(toEntity_number(dto, context));
    
    entity.setDescription(toEntity_description(dto, context));
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_number(final NumberedWithDescription in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final NumberedWithDescriptionDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_description(final NumberedWithDescription in, final MappingContext context) {
    return in.getDescription();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_description(final NumberedWithDescriptionDto in, final MappingContext context) {
    return in.getDescription();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(NumberedWithDescriptionDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(NumberedWithDescription.class, in);
  }
}
