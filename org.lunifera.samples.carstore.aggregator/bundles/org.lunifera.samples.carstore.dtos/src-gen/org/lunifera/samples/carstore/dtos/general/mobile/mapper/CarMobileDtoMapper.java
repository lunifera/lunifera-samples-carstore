package org.lunifera.samples.carstore.dtos.general.mobile.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.WeightDto;
import org.lunifera.samples.carstore.dtos.general.mobile.CarMobileDto;
import org.lunifera.samples.carstore.dtos.general.mobile.mapper.ItemMobileDtoMapper;
import org.lunifera.samples.carstore.entities.general.Car;
import org.lunifera.samples.carstore.entities.general.Weight;

/**
 * This class maps the dto {@link CarMobileDto} to and from the entity {@link Car}.
 * 
 */
@SuppressWarnings("all")
public class CarMobileDtoMapper<DTO extends CarMobileDto, ENTITY extends Car> extends ItemMobileDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Car createEntity() {
    return new Car();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CarMobileDto createDto() {
    return new CarMobileDto();
  }
  
  /**
   * Maps the entity {@link Car} to the dto {@link CarMobileDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CarMobileDto dto, final Car entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setAxes(toDto_axes(entity, context));
    dto.setWeight(toDto_weight(entity, context));
  }
  
  /**
   * Maps the dto {@link CarMobileDto} to the entity {@link Car}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CarMobileDto dto, final Car entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setAxes(toEntity_axes(dto, context));
    
    entity.setWeight(toEntity_weight(dto, context));
    
  }
  
  /**
   * Maps the property axes from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toDto_axes(final Car in, final MappingContext context) {
    return in.getAxes();
  }
  
  /**
   * Maps the property axes from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toEntity_axes(final CarMobileDto in, final MappingContext context) {
    return in.getAxes();
  }
  
  /**
   * Maps the property weight from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected WeightDto toDto_weight(final Car in, final MappingContext context) {
    if(in.getWeight() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<WeightDto, Weight> mapper = (org.lunifera.dsl.dto.lib.IMapper<WeightDto, Weight>) getToDtoMapper(WeightDto.class, in.getWeight().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	WeightDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getWeight(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property weight from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Weight toEntity_weight(final CarMobileDto in, final MappingContext context) {
    if(in.getWeight() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<WeightDto, Weight> mapper = (org.lunifera.dsl.dto.lib.IMapper<WeightDto, Weight>) getToEntityMapper(in.getWeight().getClass(), Weight.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Weight entity = mapper.createEntity();
    	mapper.mapToEntity(in.getWeight(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CarMobileDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Car.class, in);
  }
}
