package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Car;
import org.lunifera.samples.carstore.ConfigDetail;
import org.lunifera.samples.carstore.Price;
import org.lunifera.samples.carstore.dtos.CarDto;
import org.lunifera.samples.carstore.dtos.ConfigDetailDto;
import org.lunifera.samples.carstore.dtos.PriceDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link ConfigDetailDto} to and from the entity {@link ConfigDetail}.
 * 
 */
@SuppressWarnings("all")
public class ConfigDetailDtoMapper<DTO extends ConfigDetailDto, ENTITY extends ConfigDetail> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link ConfigDetail} to the dto {@link ConfigDetailDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final ConfigDetailDto dto, final ConfigDetail entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setCar(toDto_car(entity));
    dto.setNumber(toDto_number(entity));
    dto.setDescription(toDto_description(entity));
    dto.setPrice(toDto_price(entity));
  }
  
  /**
   * Maps the dto {@link ConfigDetailDto} to the entity {@link ConfigDetail}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final ConfigDetailDto dto, final ConfigDetail entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setCar(toEntity_car(dto));
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setDescription(toEntity_description(dto));
    
    entity.setPrice(toEntity_price(dto));
    
  }
  
  /**
   * Maps the property car from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected CarDto toDto_car(final ConfigDetail in) {
    org.lunifera.dsl.dto.lib.IMapper<CarDto, Car> mapper = getMapper(CarDto.class, Car.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getCar() != null) {
    	CarDto dto = new CarDto();
    	mapper.mapToDTO(dto, in.getCar());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property car from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected Car toEntity_car(final ConfigDetailDto in) {
    org.lunifera.dsl.dto.lib.IMapper<CarDto, Car> mapper = getMapper(CarDto.class, Car.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getCar() != null) {
    	Car entity = new Car();
    	mapper.mapToEntity(in.getCar(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final ConfigDetail in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final ConfigDetailDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_description(final ConfigDetail in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property description from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_description(final ConfigDetailDto in) {
    return in.getDescription();
  }
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected PriceDto toDto_price(final ConfigDetail in) {
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
  protected Price toEntity_price(final ConfigDetailDto in) {
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
}
