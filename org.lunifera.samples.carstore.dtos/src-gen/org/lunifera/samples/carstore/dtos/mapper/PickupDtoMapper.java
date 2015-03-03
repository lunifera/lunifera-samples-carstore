package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Length;
import org.lunifera.samples.carstore.Pickup;
import org.lunifera.samples.carstore.dtos.LengthDto;
import org.lunifera.samples.carstore.dtos.PickupDto;
import org.lunifera.samples.carstore.dtos.mapper.CarDtoMapper;

/**
 * This class maps the dto {@link PickupDto} to and from the entity {@link Pickup}.
 * 
 */
@SuppressWarnings("all")
public class PickupDtoMapper<DTO extends PickupDto, ENTITY extends Pickup> extends CarDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Pickup} to the dto {@link PickupDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final PickupDto dto, final Pickup entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setLoadingAreaWidth(toDto_loadingAreaWidth(entity));
    dto.setLoadingAreaLength(toDto_loadingAreaLength(entity));
  }
  
  /**
   * Maps the dto {@link PickupDto} to the entity {@link Pickup}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final PickupDto dto, final Pickup entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setLoadingAreaWidth(toEntity_loadingAreaWidth(dto));
    
    entity.setLoadingAreaLength(toEntity_loadingAreaLength(dto));
    
  }
  
  /**
   * Maps the property loadingAreaWidth from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected LengthDto toDto_loadingAreaWidth(final Pickup in) {
    org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = getMapper(LengthDto.class, Length.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
     
    if(in.getLoadingAreaWidth() != null) {
    	LengthDto dto = new LengthDto();
    	mapper.mapToDTO(dto, in.getLoadingAreaWidth());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property loadingAreaWidth from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Length toEntity_loadingAreaWidth(final PickupDto in) {
    org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = getMapper(LengthDto.class, Length.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getLoadingAreaWidth() != null) {
    	Length entity = new Length();
    	mapper.mapToEntity(in.getLoadingAreaWidth(), entity);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property loadingAreaLength from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected LengthDto toDto_loadingAreaLength(final Pickup in) {
    org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = getMapper(LengthDto.class, Length.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
     
    if(in.getLoadingAreaLength() != null) {
    	LengthDto dto = new LengthDto();
    	mapper.mapToDTO(dto, in.getLoadingAreaLength());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property loadingAreaLength from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Length toEntity_loadingAreaLength(final PickupDto in) {
    org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = getMapper(LengthDto.class, Length.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getLoadingAreaLength() != null) {
    	Length entity = new Length();
    	mapper.mapToEntity(in.getLoadingAreaLength(), entity);
    	return entity;							
    } else {
    	return null;
    }
  }
}
