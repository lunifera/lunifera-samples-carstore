package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Convertible;
import org.lunifera.samples.carstore.RoofType;
import org.lunifera.samples.carstore.dtos.ConvertibleDto;
import org.lunifera.samples.carstore.dtos.mapper.CarDtoMapper;

/**
 * This class maps the dto {@link ConvertibleDto} to and from the entity {@link Convertible}.
 * 
 */
@SuppressWarnings("all")
public class ConvertibleDtoMapper<DTO extends ConvertibleDto, ENTITY extends Convertible> extends CarDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Convertible} to the dto {@link ConvertibleDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final ConvertibleDto dto, final Convertible entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setRoofType(toDto_roofType(entity));
    dto.setColor(toDto_color(entity));
  }
  
  /**
   * Maps the dto {@link ConvertibleDto} to the entity {@link Convertible}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final ConvertibleDto dto, final Convertible entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setRoofType(toEntity_roofType(dto));
    
    entity.setColor(toEntity_color(dto));
    
  }
  
  /**
   * Maps the property roofType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected RoofType toDto_roofType(final Convertible in) {
    return in.getRoofType();
  }
  
  /**
   * Maps the property roofType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected RoofType toEntity_roofType(final ConvertibleDto in) {
    return in.getRoofType();
  }
  
  /**
   * Maps the property color from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_color(final Convertible in) {
    return in.getColor();
  }
  
  /**
   * Maps the property color from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_color(final ConvertibleDto in) {
    return in.getColor();
  }
}
