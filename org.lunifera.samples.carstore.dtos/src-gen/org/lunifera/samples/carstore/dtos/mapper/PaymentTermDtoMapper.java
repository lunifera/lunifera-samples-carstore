package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.PaymentTerm;
import org.lunifera.samples.carstore.dtos.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link PaymentTermDto} to and from the entity {@link PaymentTerm}.
 * 
 */
@SuppressWarnings("all")
public class PaymentTermDtoMapper<DTO extends PaymentTermDto, ENTITY extends PaymentTerm> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link PaymentTerm} to the dto {@link PaymentTermDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final PaymentTermDto dto, final PaymentTerm entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setNumber(toDto_number(entity));
    dto.setDescription(toDto_description(entity));
  }
  
  /**
   * Maps the dto {@link PaymentTermDto} to the entity {@link PaymentTerm}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final PaymentTermDto dto, final PaymentTerm entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setDescription(toEntity_description(dto));
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final PaymentTerm in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final PaymentTermDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_description(final PaymentTerm in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_description(final PaymentTermDto in) {
    return in.getDescription();
  }
}
