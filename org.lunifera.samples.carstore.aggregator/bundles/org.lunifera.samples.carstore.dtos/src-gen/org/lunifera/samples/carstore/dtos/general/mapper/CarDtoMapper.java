package org.lunifera.samples.carstore.dtos.general.mapper;

import java.util.List;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.dtos.general.WeightDto;
import org.lunifera.samples.carstore.dtos.general.mapper.ItemDtoMapper;
import org.lunifera.samples.carstore.entities.general.Car;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.Weight;

/**
 * This class maps the dto {@link CarDto} to and from the entity {@link Car}.
 * 
 */
@SuppressWarnings("all")
public class CarDtoMapper<DTO extends CarDto, ENTITY extends Car> extends ItemDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Car createEntity() {
    return new Car();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CarDto createDto() {
    return new CarDto();
  }
  
  /**
   * Maps the entity {@link Car} to the dto {@link CarDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CarDto dto, final Car entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setAxes(toDto_axes(entity, context));
    dto.setWeight(toDto_weight(entity, context));
    dto.setPrice(toDto_price(entity, context));
    for(org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto _dtoValue : toDto_configDetails(entity, context)) {
    	dto.addToConfigDetails(_dtoValue);
    }
  }
  
  /**
   * Maps the dto {@link CarDto} to the entity {@link Car}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CarDto dto, final Car entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setAxes(toEntity_axes(dto, context));
    
    entity.setWeight(toEntity_weight(dto, context));
    
    entity.setPrice(toEntity_price(dto, context));
    
    List<ConfigDetailDefinition> configDetails_entities = new java.util.ArrayList<ConfigDetailDefinition>();
    for(ConfigDetailDefinition _entityValue : toEntity_configDetails(dto, context)) {
    	configDetails_entities.add(_entityValue);
    }
    entity.setConfigDetails(configDetails_entities);
    
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
  protected int toEntity_axes(final CarDto in, final MappingContext context) {
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
  protected Weight toEntity_weight(final CarDto in, final MappingContext context) {
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
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected PriceDto toDto_price(final Car in, final MappingContext context) {
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
  protected Price toEntity_price(final CarDto in, final MappingContext context) {
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
   * Maps the property configDetails from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return A list of mapped dtos
   * 
   */
  protected List<ConfigDetailDefinitionDto> toDto_configDetails(final Car in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDefinitionDto, ConfigDetailDefinition> mapper = getToDtoMapper(ConfigDetailDefinitionDto.class, ConfigDetailDefinition.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<ConfigDetailDefinitionDto> results = new java.util.ArrayList<ConfigDetailDefinitionDto>();
    for (ConfigDetailDefinition _entity : in.getConfigDetails()) {
    	ConfigDetailDefinitionDto _dto = context.get(mapper.createDtoHash(_entity));
    	if (_dto == null) {
    		_dto = mapper.createDto();
    		mapper.mapToDTO(_dto, _entity, context);
    	} else {
    		if(context.isRefresh()){
    			mapper.mapToDTO(_dto, _entity, context);
    		}
    	}
    	results.add(_dto);
    }
    return results;
  }
  
  /**
   * Maps the property configDetails from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return A list of mapped entities
   * 
   */
  protected List<ConfigDetailDefinition> toEntity_configDetails(final CarDto in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDefinitionDto, ConfigDetailDefinition> mapper = getToEntityMapper(ConfigDetailDefinitionDto.class, ConfigDetailDefinition.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<ConfigDetailDefinition> results = new java.util.ArrayList<ConfigDetailDefinition>();
    for (ConfigDetailDefinitionDto _dto : in.getConfigDetails()) {
    	ConfigDetailDefinition _entity = context.get(mapper.createEntityHash(_dto));
    	if(_entity == null) {
    		_entity = mapper.createEntity();
    		mapper.mapToEntity(_dto, _entity, context);
    	}
    	results.add(_entity);
    }
    return results;
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CarDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Car.class, in);
  }
}
