package org.lunifera.samples.carstore.dtos.general.mobile.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.mobile.ConvertibleMobileDto;
import org.lunifera.samples.carstore.dtos.general.mobile.mapper.CarMobileDtoMapper;
import org.lunifera.samples.carstore.entities.general.Convertible;

/**
 * This class maps the dto {@link ConvertibleMobileDto} to and from the entity {@link Convertible}.
 * 
 */
@SuppressWarnings("all")
public class ConvertibleMobileDtoMapper<DTO extends ConvertibleMobileDto, ENTITY extends Convertible> extends CarMobileDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Convertible createEntity() {
    return new Convertible();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public ConvertibleMobileDto createDto() {
    return new ConvertibleMobileDto();
  }
  
  /**
   * Maps the entity {@link Convertible} to the dto {@link ConvertibleMobileDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final ConvertibleMobileDto dto, final Convertible entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setColor(toDto_color(entity, context));
  }
  
  /**
   * Maps the dto {@link ConvertibleMobileDto} to the entity {@link Convertible}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final ConvertibleMobileDto dto, final Convertible entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setColor(toEntity_color(dto, context));
    
  }
  
  /**
   * Maps the property color from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_color(final Convertible in, final MappingContext context) {
    return in.getColor();
  }
  
  /**
   * Maps the property color from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_color(final ConvertibleMobileDto in, final MappingContext context) {
    return in.getColor();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ConvertibleMobileDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Convertible.class, in);
  }
}
