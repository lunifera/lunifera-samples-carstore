package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Currency;
import org.lunifera.samples.carstore.dtos.CurrencyDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link CurrencyDto} to and from the entity {@link Currency}.
 * 
 */
@SuppressWarnings("all")
public class CurrencyDtoMapper<DTO extends CurrencyDto, ENTITY extends Currency> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Currency} to the dto {@link CurrencyDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final CurrencyDto dto, final Currency entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setNumber(toDto_number(entity));
    dto.setDescription(toDto_description(entity));
    dto.setIso3Code(toDto_iso3Code(entity));
  }
  
  /**
   * Maps the dto {@link CurrencyDto} to the entity {@link Currency}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final CurrencyDto dto, final Currency entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setDescription(toEntity_description(dto));
    
    entity.setIso3Code(toEntity_iso3Code(dto));
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final Currency in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final CurrencyDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_description(final Currency in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_description(final CurrencyDto in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property iso3Code from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_iso3Code(final Currency in) {
    return in.getIso3Code();
  }
  
  /**
   * Maps the property iso3Code from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_iso3Code(final CurrencyDto in) {
    return in.getIso3Code();
  }
}
