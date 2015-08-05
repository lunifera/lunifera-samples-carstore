package org.lunifera.samples.carstore.dtos.sales.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.sales.ManufacturerOrderDto;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;
import org.lunifera.samples.carstore.entities.sales.ManufacturerOrder;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

/**
 * This class maps the dto {@link ManufacturerOrderDto} to and from the entity {@link ManufacturerOrder}.
 * 
 */
@SuppressWarnings("all")
public class ManufacturerOrderDtoMapper<DTO extends ManufacturerOrderDto, ENTITY extends ManufacturerOrder> implements IMapper<DTO, ENTITY> {
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
  public ManufacturerOrder createEntity() {
    return new ManufacturerOrder();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public ManufacturerOrderDto createDto() {
    return new ManufacturerOrderDto();
  }
  
  /**
   * Maps the entity {@link ManufacturerOrder} to the dto {@link ManufacturerOrderDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final ManufacturerOrderDto dto, final ManufacturerOrder entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setSalesOrderDetail(toDto_salesOrderDetail(entity, context));
    dto.setId(toDto_id(entity, context));
  }
  
  /**
   * Maps the dto {@link ManufacturerOrderDto} to the entity {@link ManufacturerOrder}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final ManufacturerOrderDto dto, final ManufacturerOrder entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
    entity.setNumber(toEntity_number(dto, context));
    entity.setSalesOrderDetail(toEntity_salesOrderDetail(dto, context));
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
  protected String toDto_number(final ManufacturerOrder in, final MappingContext context) {
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
  protected String toEntity_number(final ManufacturerOrderDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property salesOrderDetail from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected SalesOrderDetailDto toDto_salesOrderDetail(final ManufacturerOrder in, final MappingContext context) {
    if(in.getSalesOrderDetail() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail> mapper = (org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail>) getToDtoMapper(SalesOrderDetailDto.class, in.getSalesOrderDetail().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	SalesOrderDetailDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getSalesOrderDetail()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getSalesOrderDetail(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getSalesOrderDetail(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property salesOrderDetail from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected SalesOrderDetail toEntity_salesOrderDetail(final ManufacturerOrderDto in, final MappingContext context) {
    if(in.getSalesOrderDetail() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail> mapper = (org.lunifera.dsl.dto.lib.IMapper<SalesOrderDetailDto, SalesOrderDetail>) getToEntityMapper(in.getSalesOrderDetail().getClass(), SalesOrderDetail.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	SalesOrderDetail entity = null;
    	entity = context.get(mapper.createEntityHash(in.getSalesOrderDetail()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getSalesOrderDetail(), entity, context);	
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
  protected String toDto_id(final ManufacturerOrder in, final MappingContext context) {
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
  protected String toEntity_id(final ManufacturerOrderDto in, final MappingContext context) {
    return in.getId();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ManufacturerOrderDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(ManufacturerOrder.class, in);
  }
}
