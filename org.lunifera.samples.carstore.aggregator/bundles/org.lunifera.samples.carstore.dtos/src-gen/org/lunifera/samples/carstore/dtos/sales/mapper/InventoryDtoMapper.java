package org.lunifera.samples.carstore.dtos.sales.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.QuantityDto;
import org.lunifera.samples.carstore.dtos.sales.InventoryDto;
import org.lunifera.samples.carstore.entities.general.Quantity;
import org.lunifera.samples.carstore.entities.sales.Inventory;

/**
 * This class maps the dto {@link InventoryDto} to and from the entity {@link Inventory}.
 * 
 */
@SuppressWarnings("all")
public class InventoryDtoMapper<DTO extends InventoryDto, ENTITY extends Inventory> implements IMapper<DTO, ENTITY> {
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
  public Inventory createEntity() {
    return new Inventory();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public InventoryDto createDto() {
    return new InventoryDto();
  }
  
  /**
   * Maps the entity {@link Inventory} to the dto {@link InventoryDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final InventoryDto dto, final Inventory entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    dto.setRequestedQuantity(toDto_requestedQuantity(entity, context));
    dto.setCountedQuantity(toDto_countedQuantity(entity, context));
    dto.setId(toDto_id(entity, context));
  }
  
  /**
   * Maps the dto {@link InventoryDto} to the entity {@link Inventory}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final InventoryDto dto, final Inventory entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
    entity.setRequestedQuantity(toEntity_requestedQuantity(dto, context));
    entity.setCountedQuantity(toEntity_countedQuantity(dto, context));
    entity.setId(toEntity_id(dto, context));
  }
  
  /**
   * Maps the property requestedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected QuantityDto toDto_requestedQuantity(final Inventory in, final MappingContext context) {
    if(in.getRequestedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToDtoMapper(QuantityDto.class, in.getRequestedQuantity().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	QuantityDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getRequestedQuantity(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property requestedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Quantity toEntity_requestedQuantity(final InventoryDto in, final MappingContext context) {
    if(in.getRequestedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToEntityMapper(in.getRequestedQuantity().getClass(), Quantity.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Quantity entity = mapper.createEntity();
    	mapper.mapToEntity(in.getRequestedQuantity(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property countedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected QuantityDto toDto_countedQuantity(final Inventory in, final MappingContext context) {
    if(in.getCountedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToDtoMapper(QuantityDto.class, in.getCountedQuantity().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	QuantityDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getCountedQuantity(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property countedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Quantity toEntity_countedQuantity(final InventoryDto in, final MappingContext context) {
    if(in.getCountedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToEntityMapper(in.getCountedQuantity().getClass(), Quantity.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Quantity entity = mapper.createEntity();
    	mapper.mapToEntity(in.getCountedQuantity(), entity, context);
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
  protected String toDto_id(final Inventory in, final MappingContext context) {
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
  protected String toEntity_id(final InventoryDto in, final MappingContext context) {
    return in.getId();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(InventoryDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Inventory.class, in);
  }
}
