package org.lunifera.samples.carstore.dtos.sales.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.sales.CarReceiptDto;
import org.lunifera.samples.carstore.dtos.sales.ManufacturerOrderDto;
import org.lunifera.samples.carstore.entities.sales.CarReceipt;
import org.lunifera.samples.carstore.entities.sales.ManufacturerOrder;

/**
 * This class maps the dto {@link CarReceiptDto} to and from the entity {@link CarReceipt}.
 * 
 */
@SuppressWarnings("all")
public class CarReceiptDtoMapper<DTO extends CarReceiptDto, ENTITY extends CarReceipt> implements IMapper<DTO, ENTITY> {
  private IMapperAccess mapperAccess;
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getToDtoMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getToDtoMapper(dtoClass, entityClass);
  }
  
  /**
   * Returns the mapper instance that may map between the given dto and entity. Or <code>null</code> if no mapper is available.
   * 
   * @param dtoClass - the class of the dto that should be mapped
   * @param entityClass - the class of the entity that should be mapped
   * @return the mapper instance or <code>null</code>
   */
  protected <D, E> IMapper<D, E> getToEntityMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getToEntityMapper(dtoClass, entityClass);
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void bindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = mapperAccess;
  }
  
  /**
   * Called by OSGi-DS. Binds the mapper access service.
   * 
   * @param service - The mapper access service
   * 
   */
  protected void unbindMapperAccess(final IMapperAccess mapperAccess) {
    this.mapperAccess = null;
  }
  
  /**
   * Creates a new instance of the entity
   */
  public CarReceipt createEntity() {
    return new CarReceipt();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CarReceiptDto createDto() {
    return new CarReceiptDto();
  }
  
  /**
   * Maps the entity {@link CarReceipt} to the dto {@link CarReceiptDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CarReceiptDto dto, final CarReceipt entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setOrder(toDto_order(entity, context));
    dto.setId(toDto_id(entity, context));
  }
  
  /**
   * Maps the dto {@link CarReceiptDto} to the entity {@link CarReceipt}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CarReceiptDto dto, final CarReceipt entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
    entity.setNumber(toEntity_number(dto, context));
    entity.setOrder(toEntity_order(dto, context));
    entity.setId(toEntity_id(dto, context));
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_number(final CarReceipt in, final MappingContext context) {
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
  protected String toEntity_number(final CarReceiptDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property order from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected ManufacturerOrderDto toDto_order(final CarReceipt in, final MappingContext context) {
    if(in.getOrder() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ManufacturerOrderDto, ManufacturerOrder> mapper = (org.lunifera.dsl.dto.lib.IMapper<ManufacturerOrderDto, ManufacturerOrder>) getToDtoMapper(ManufacturerOrderDto.class, in.getOrder().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	ManufacturerOrderDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getOrder()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getOrder(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getOrder(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property order from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected ManufacturerOrder toEntity_order(final CarReceiptDto in, final MappingContext context) {
    if(in.getOrder() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ManufacturerOrderDto, ManufacturerOrder> mapper = (org.lunifera.dsl.dto.lib.IMapper<ManufacturerOrderDto, ManufacturerOrder>) getToEntityMapper(in.getOrder().getClass(), ManufacturerOrder.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	ManufacturerOrder entity = null;
    	entity = context.get(mapper.createEntityHash(in.getOrder()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getOrder(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_id(final CarReceipt in, final MappingContext context) {
    return in.getId();
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_id(final CarReceiptDto in, final MappingContext context) {
    return in.getId();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CarReceiptDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CarReceipt.class, in);
  }
}
