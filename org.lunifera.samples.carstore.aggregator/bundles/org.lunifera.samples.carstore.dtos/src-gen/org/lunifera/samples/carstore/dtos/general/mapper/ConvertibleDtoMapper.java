package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.ConvertibleDto;
import org.lunifera.samples.carstore.dtos.general.RoofType;
import org.lunifera.samples.carstore.dtos.general.mapper.CarDtoMapper;
import org.lunifera.samples.carstore.entities.general.Convertible;

/**
 * This class maps the dto {@link ConvertibleDto} to and from the entity {@link Convertible}.
 * 
 */
@SuppressWarnings("all")
public class ConvertibleDtoMapper<DTO extends ConvertibleDto, ENTITY extends Convertible> extends CarDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Convertible createEntity() {
    return new Convertible();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public ConvertibleDto createDto() {
    return new ConvertibleDto();
  }
  
  /**
   * Maps the entity {@link Convertible} to the dto {@link ConvertibleDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final ConvertibleDto dto, final Convertible entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setRoofType(toDto_roofType(entity, context));
    dto.setColor(toDto_color(entity, context));
  }
  
  /**
   * Maps the dto {@link ConvertibleDto} to the entity {@link Convertible}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final ConvertibleDto dto, final Convertible entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setRoofType(toEntity_roofType(dto, context));
    
    entity.setColor(toEntity_color(dto, context));
    
  }
  
  /**
   * Maps the property roofType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected RoofType toDto_roofType(final Convertible in, final MappingContext context) {
    if(in.getRoofType() != null) {
    	return org.lunifera.samples.carstore.dtos.general.RoofType.valueOf(in.getRoofType().name());
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property roofType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected org.lunifera.samples.carstore.entities.general.RoofType toEntity_roofType(final ConvertibleDto in, final MappingContext context) {
    if(in.getRoofType() != null) {
    	return org.lunifera.samples.carstore.entities.general.RoofType.valueOf(in.getRoofType().name());
    } else {
    	return null;
    }
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
  protected String toEntity_color(final ConvertibleDto in, final MappingContext context) {
    return in.getColor();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ConvertibleDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Convertible.class, in);
  }
}
