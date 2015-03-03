package org.lunifera.samples.carstore.dtos.mapper;

import java.util.List;
import org.lunifera.samples.carstore.Car;
import org.lunifera.samples.carstore.ConfigDetail;
import org.lunifera.samples.carstore.Price;
import org.lunifera.samples.carstore.Weight;
import org.lunifera.samples.carstore.dtos.CarDto;
import org.lunifera.samples.carstore.dtos.ConfigDetailDto;
import org.lunifera.samples.carstore.dtos.PriceDto;
import org.lunifera.samples.carstore.dtos.WeightDto;
import org.lunifera.samples.carstore.dtos.mapper.ItemDtoMapper;

/**
 * This class maps the dto {@link CarDto} to and from the entity {@link Car}.
 * 
 */
@SuppressWarnings("all")
public class CarDtoMapper<DTO extends CarDto, ENTITY extends Car> extends ItemDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Car} to the dto {@link CarDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final CarDto dto, final Car entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setAxes(toDto_axes(entity));
    dto.setWeight(toDto_weight(entity));
    dto.setPrice(toDto_price(entity));
    for(org.lunifera.samples.carstore.dtos.ConfigDetailDto _dtoValue : toDto_configDetails(entity)) {
    	dto.addToConfigDetails(_dtoValue);
    }
  }
  
  /**
   * Maps the dto {@link CarDto} to the entity {@link Car}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final CarDto dto, final Car entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setAxes(toEntity_axes(dto));
    
    entity.setWeight(toEntity_weight(dto));
    
    entity.setPrice(toEntity_price(dto));
    
    List<ConfigDetail> configDetails_entities = new java.util.ArrayList<ConfigDetail>();
    for(ConfigDetail _entityValue : toEntity_configDetails(dto)) {
    	configDetails_entities.add(_entityValue);
    }
    entity.setConfigDetails(configDetails_entities);
    
  }
  
  /**
   * Maps the property axes from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toDto_axes(final Car in) {
    return in.getAxes();
  }
  
  /**
   * Maps the property axes from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toEntity_axes(final CarDto in) {
    return in.getAxes();
  }
  
  /**
   * Maps the property weight from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected WeightDto toDto_weight(final Car in) {
    org.lunifera.dsl.dto.lib.IMapper<WeightDto, Weight> mapper = getMapper(WeightDto.class, Weight.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
     
    if(in.getWeight() != null) {
    	WeightDto dto = new WeightDto();
    	mapper.mapToDTO(dto, in.getWeight());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property weight from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Weight toEntity_weight(final CarDto in) {
    org.lunifera.dsl.dto.lib.IMapper<WeightDto, Weight> mapper = getMapper(WeightDto.class, Weight.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getWeight() != null) {
    	Weight entity = new Weight();
    	mapper.mapToEntity(in.getWeight(), entity);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected PriceDto toDto_price(final Car in) {
    org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price> mapper = getMapper(PriceDto.class, Price.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
     
    if(in.getPrice() != null) {
    	PriceDto dto = new PriceDto();
    	mapper.mapToDTO(dto, in.getPrice());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Price toEntity_price(final CarDto in) {
    org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price> mapper = getMapper(PriceDto.class, Price.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getPrice() != null) {
    	Price entity = new Price();
    	mapper.mapToEntity(in.getPrice(), entity);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property configDetails from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return A list of mapped dtos
   * 
   */
  protected List<ConfigDetailDto> toDto_configDetails(final Car in) {
    org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDto, ConfigDetail> mapper = getMapper(ConfigDetailDto.class, ConfigDetail.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<ConfigDetailDto> results = new java.util.ArrayList<ConfigDetailDto>();
    for (ConfigDetail _entity : in.getConfigDetails()) {
    	ConfigDetailDto _dto = new ConfigDetailDto();
    	mapper.mapToDTO(_dto, _entity);
    	results.add(_dto);
    }
    return results;
  }
  
  /**
   * Maps the property configDetails from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return A list of mapped entities
   * 
   */
  protected List<ConfigDetail> toEntity_configDetails(final CarDto in) {
    org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDto, ConfigDetail> mapper = getMapper(ConfigDetailDto.class, ConfigDetail.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<ConfigDetail> results = new java.util.ArrayList<ConfigDetail>();
    for (ConfigDetailDto _dto : in.getConfigDetails()) {
    	ConfigDetail _entity = new ConfigDetail();
    	mapper.mapToEntity(_dto, _entity);
    	results.add(_entity);
    }
    return results;
  }
}
