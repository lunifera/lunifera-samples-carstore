package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Item;
import org.lunifera.samples.carstore.UnitOfMeasure;
import org.lunifera.samples.carstore.dtos.ItemDto;
import org.lunifera.samples.carstore.dtos.UnitOfMeasureDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link ItemDto} to and from the entity {@link Item}.
 * 
 */
@SuppressWarnings("all")
public class ItemDtoMapper<DTO extends ItemDto, ENTITY extends Item> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Item} to the dto {@link ItemDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final ItemDto dto, final Item entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setNumber(toDto_number(entity));
    dto.setDescription(toDto_description(entity));
    dto.setUom(toDto_uom(entity));
  }
  
  /**
   * Maps the dto {@link ItemDto} to the entity {@link Item}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final ItemDto dto, final Item entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setDescription(toEntity_description(dto));
    
    entity.setUom(toEntity_uom(dto));
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final Item in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final ItemDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_description(final Item in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_description(final ItemDto in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property uom from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected UnitOfMeasureDto toDto_uom(final Item in) {
    org.lunifera.dsl.dto.lib.IMapper<UnitOfMeasureDto, UnitOfMeasure> mapper = getMapper(UnitOfMeasureDto.class, UnitOfMeasure.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getUom() != null) {
    	UnitOfMeasureDto dto = new UnitOfMeasureDto();
    	mapper.mapToDTO(dto, in.getUom());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property uom from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected UnitOfMeasure toEntity_uom(final ItemDto in) {
    org.lunifera.dsl.dto.lib.IMapper<UnitOfMeasureDto, UnitOfMeasure> mapper = getMapper(UnitOfMeasureDto.class, UnitOfMeasure.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getUom() != null) {
    	UnitOfMeasure entity = new UnitOfMeasure();
    	mapper.mapToEntity(in.getUom(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
}
