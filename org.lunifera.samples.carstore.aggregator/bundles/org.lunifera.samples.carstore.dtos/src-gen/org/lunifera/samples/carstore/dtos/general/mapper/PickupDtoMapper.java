package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.LengthDto;
import org.lunifera.samples.carstore.dtos.general.PickupDto;
import org.lunifera.samples.carstore.dtos.general.mapper.CarDtoMapper;
import org.lunifera.samples.carstore.entities.general.Length;
import org.lunifera.samples.carstore.entities.general.Pickup;

/**
 * This class maps the dto {@link PickupDto} to and from the entity {@link Pickup}.
 * 
 */
@SuppressWarnings("all")
public class PickupDtoMapper<DTO extends PickupDto, ENTITY extends Pickup> extends CarDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Pickup createEntity() {
    return new Pickup();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public PickupDto createDto() {
    return new PickupDto();
  }
  
  /**
   * Maps the entity {@link Pickup} to the dto {@link PickupDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final PickupDto dto, final Pickup entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setLoadingAreaWidth(toDto_loadingAreaWidth(entity, context));
    dto.setLoadingAreaLength(toDto_loadingAreaLength(entity, context));
  }
  
  /**
   * Maps the dto {@link PickupDto} to the entity {@link Pickup}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final PickupDto dto, final Pickup entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setLoadingAreaWidth(toEntity_loadingAreaWidth(dto, context));
    
    entity.setLoadingAreaLength(toEntity_loadingAreaLength(dto, context));
    
  }
  
  /**
   * Maps the property loadingAreaWidth from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected LengthDto toDto_loadingAreaWidth(final Pickup in, final MappingContext context) {
    if(in.getLoadingAreaWidth() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = (org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length>) getToDtoMapper(LengthDto.class, in.getLoadingAreaWidth().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	LengthDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getLoadingAreaWidth(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property loadingAreaWidth from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Length toEntity_loadingAreaWidth(final PickupDto in, final MappingContext context) {
    if(in.getLoadingAreaWidth() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = (org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length>) getToEntityMapper(in.getLoadingAreaWidth().getClass(), Length.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Length entity = mapper.createEntity();
    	mapper.mapToEntity(in.getLoadingAreaWidth(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property loadingAreaLength from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected LengthDto toDto_loadingAreaLength(final Pickup in, final MappingContext context) {
    if(in.getLoadingAreaLength() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = (org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length>) getToDtoMapper(LengthDto.class, in.getLoadingAreaLength().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	LengthDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getLoadingAreaLength(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property loadingAreaLength from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Length toEntity_loadingAreaLength(final PickupDto in, final MappingContext context) {
    if(in.getLoadingAreaLength() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length> mapper = (org.lunifera.dsl.dto.lib.IMapper<LengthDto, Length>) getToEntityMapper(in.getLoadingAreaLength().getClass(), Length.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Length entity = mapper.createEntity();
    	mapper.mapToEntity(in.getLoadingAreaLength(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(PickupDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Pickup.class, in);
  }
}
