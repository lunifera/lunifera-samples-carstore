package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.SITypeEnum;
import org.lunifera.samples.carstore.UOMFamilyEnum;
import org.lunifera.samples.carstore.UnitOfMeasure;
import org.lunifera.samples.carstore.dtos.UnitOfMeasureDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link UnitOfMeasureDto} to and from the entity {@link UnitOfMeasure}.
 * 
 */
@SuppressWarnings("all")
public class UnitOfMeasureDtoMapper<DTO extends UnitOfMeasureDto, ENTITY extends UnitOfMeasure> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link UnitOfMeasure} to the dto {@link UnitOfMeasureDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final UnitOfMeasureDto dto, final UnitOfMeasure entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setNumber(toDto_number(entity));
    dto.setName(toDto_name(entity));
    dto.setSiType(toDto_siType(entity));
    dto.setFamily(toDto_family(entity));
  }
  
  /**
   * Maps the dto {@link UnitOfMeasureDto} to the entity {@link UnitOfMeasure}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final UnitOfMeasureDto dto, final UnitOfMeasure entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setName(toEntity_name(dto));
    
    entity.setSiType(toEntity_siType(dto));
    
    entity.setFamily(toEntity_family(dto));
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final UnitOfMeasure in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final UnitOfMeasureDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_name(final UnitOfMeasure in) {
    return in.getName();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_name(final UnitOfMeasureDto in) {
    return in.getName();
  }
  
  /**
   * Maps the property siType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected SITypeEnum toDto_siType(final UnitOfMeasure in) {
    return in.getSiType();
  }
  
  /**
   * Maps the property siType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected SITypeEnum toEntity_siType(final UnitOfMeasureDto in) {
    return in.getSiType();
  }
  
  /**
   * Maps the property family from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected UOMFamilyEnum toDto_family(final UnitOfMeasure in) {
    return in.getFamily();
  }
  
  /**
   * Maps the property family from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected UOMFamilyEnum toEntity_family(final UnitOfMeasureDto in) {
    return in.getFamily();
  }
}
