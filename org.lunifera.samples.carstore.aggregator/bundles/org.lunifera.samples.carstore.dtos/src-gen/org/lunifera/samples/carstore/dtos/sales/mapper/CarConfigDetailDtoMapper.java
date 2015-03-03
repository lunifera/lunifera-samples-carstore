package org.lunifera.samples.carstore.dtos.sales.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.dtos.sales.CarConfigDetailDto;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.sales.CarConfigDetail;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

/**
 * This class maps the dto {@link CarConfigDetailDto} to and from the entity {@link CarConfigDetail}.
 * 
 */
@SuppressWarnings("all")
public class CarConfigDetailDtoMapper<DTO extends CarConfigDetailDto, ENTITY extends CarConfigDetail> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public CarConfigDetail createEntity() {
    return new CarConfigDetail();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CarConfigDetailDto createDto() {
    return new CarConfigDetailDto();
  }
  
  /**
   * Maps the entity {@link CarConfigDetail} to the dto {@link CarConfigDetailDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CarConfigDetailDto dto, final CarConfigDetail entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setConfigDef(toDto_configDef(entity, context));
  }
  
  /**
   * Maps the dto {@link CarConfigDetailDto} to the entity {@link CarConfigDetail}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CarConfigDetailDto dto, final CarConfigDetail entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setNumber(toEntity_number(dto, context));
    
    
    entity.setConfigDef(toEntity_configDef(dto, context));
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toDto_number(final CarConfigDetail in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toEntity_number(final CarConfigDetailDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property parent from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected SalesOrderDetailDto toDto_parent(final CarConfigDetail in, final MappingContext context) {
    if(in.getParent() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail> mapper = (org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail>) getToDtoMapper(SalesOrderDetailDto.class, in.getParent().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	SalesOrderDetailDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getParent()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getParent(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getParent(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property parent from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected SalesOrderDetail toEntity_parent(final CarConfigDetailDto in, final MappingContext context) {
    if(in.getParent() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail> mapper = (org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail>) getToEntityMapper(in.getParent().getClass(), SalesOrderDetail.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	SalesOrderDetail entity = null;
    	entity = context.get(mapper.createEntityHash(in.getParent()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getParent(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property configDef from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected ConfigDetailDefinitionDto toDto_configDef(final CarConfigDetail in, final MappingContext context) {
    if(in.getConfigDef() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDefinitionDto, ConfigDetailDefinition> mapper = (org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDefinitionDto, ConfigDetailDefinition>) getToDtoMapper(ConfigDetailDefinitionDto.class, in.getConfigDef().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	ConfigDetailDefinitionDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getConfigDef()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getConfigDef(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getConfigDef(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property configDef from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected ConfigDetailDefinition toEntity_configDef(final CarConfigDetailDto in, final MappingContext context) {
    if(in.getConfigDef() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDefinitionDto, ConfigDetailDefinition> mapper = (org.lunifera.dsl.dto.lib.IMapper<ConfigDetailDefinitionDto, ConfigDetailDefinition>) getToEntityMapper(in.getConfigDef().getClass(), ConfigDetailDefinition.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	ConfigDetailDefinition entity = null;
    	entity = context.get(mapper.createEntityHash(in.getConfigDef()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getConfigDef(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CarConfigDetailDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CarConfigDetail.class, in);
  }
}
