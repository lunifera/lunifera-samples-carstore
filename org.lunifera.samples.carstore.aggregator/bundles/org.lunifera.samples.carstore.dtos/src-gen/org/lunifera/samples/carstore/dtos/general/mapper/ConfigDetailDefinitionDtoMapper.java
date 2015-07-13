package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.dtos.general.mapper.NumberedWithDescriptionDtoMapper;
import org.lunifera.samples.carstore.entities.general.Car;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.general.Price;

/**
 * This class maps the dto {@link ConfigDetailDefinitionDto} to and from the entity {@link ConfigDetailDefinition}.
 * 
 */
@SuppressWarnings("all")
public class ConfigDetailDefinitionDtoMapper<DTO extends ConfigDetailDefinitionDto, ENTITY extends ConfigDetailDefinition> extends NumberedWithDescriptionDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public ConfigDetailDefinition createEntity() {
    return new ConfigDetailDefinition();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public ConfigDetailDefinitionDto createDto() {
    return new ConfigDetailDefinitionDto();
  }
  
  /**
   * Maps the entity {@link ConfigDetailDefinition} to the dto {@link ConfigDetailDefinitionDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final ConfigDetailDefinitionDto dto, final ConfigDetailDefinition entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setPrice(toDto_price(entity, context));
    if(dto.getCar() == null) {
    	// car is container property. So check for null to avoid looping
    	dto.setCar(toDto_car(entity, context));
    }
  }
  
  /**
   * Maps the dto {@link ConfigDetailDefinitionDto} to the entity {@link ConfigDetailDefinition}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final ConfigDetailDefinitionDto dto, final ConfigDetailDefinition entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    entity.setPrice(toEntity_price(dto, context));
    if(entity.getCar() == null) {
    	// car is container property. So check for null to avoid looping
    	entity.setCar(toEntity_car(dto, context));
    }
  }
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected PriceDto toDto_price(final ConfigDetailDefinition in, final MappingContext context) {
    if(in.getPrice() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price> mapper = (org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price>) getToDtoMapper(PriceDto.class, in.getPrice().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	PriceDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getPrice(), context);
    	return dto;
    } else {
    	return null;
    }
  }
   
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Price toEntity_price(final ConfigDetailDefinitionDto in, final MappingContext context) {
    if(in.getPrice() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price> mapper = (org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price>) getToEntityMapper(in.getPrice().getClass(), Price.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Price entity = mapper.createEntity();
    	mapper.mapToEntity(in.getPrice(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property car from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected CarDto toDto_car(final ConfigDetailDefinition in, final MappingContext context) {
    if(in.getCar() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CarDto, Car> mapper = (org.lunifera.dsl.dto.lib.IMapper<CarDto, Car>) getToDtoMapper(CarDto.class, in.getCar().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	CarDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getCar()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getCar(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getCar(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property car from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected Car toEntity_car(final ConfigDetailDefinitionDto in, final MappingContext context) {
    if(in.getCar() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CarDto, Car> mapper = (org.lunifera.dsl.dto.lib.IMapper<CarDto, Car>) getToEntityMapper(in.getCar().getClass(), Car.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Car entity = null;
    	entity = context.get(mapper.createEntityHash(in.getCar()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getCar(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ConfigDetailDefinitionDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ConfigDetailDefinition.class, in);
  }
}
