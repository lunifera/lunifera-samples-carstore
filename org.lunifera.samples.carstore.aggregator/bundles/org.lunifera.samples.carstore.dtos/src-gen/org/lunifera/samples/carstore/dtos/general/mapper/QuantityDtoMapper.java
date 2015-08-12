package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.QuantityDto;
import org.lunifera.samples.carstore.dtos.general.UnitOfMeasureDto;
import org.lunifera.samples.carstore.entities.general.Quantity;
import org.lunifera.samples.carstore.entities.general.UnitOfMeasure;

/**
 * This class maps the dto {@link QuantityDto} to and from the entity {@link Quantity}.
 * 
 */
@SuppressWarnings("all")
public class QuantityDtoMapper<DTO extends QuantityDto, ENTITY extends Quantity> implements IMapper<DTO, ENTITY> {
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
  public Quantity createEntity() {
    return new Quantity();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public QuantityDto createDto() {
    return new QuantityDto();
  }
  
  /**
   * Maps the entity {@link Quantity} to the dto {@link QuantityDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final QuantityDto dto, final Quantity entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    dto.setAmount(toDto_amount(entity, context));
    dto.setUom(toDto_uom(entity, context));
  }
  
  /**
   * Maps the dto {@link QuantityDto} to the entity {@link Quantity}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final QuantityDto dto, final Quantity entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    
    entity.setAmount(toEntity_amount(dto, context));
    entity.setUom(toEntity_uom(dto, context));
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected float toDto_amount(final Quantity in, final MappingContext context) {
    return in.getAmount();
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected float toEntity_amount(final QuantityDto in, final MappingContext context) {
    return in.getAmount();
  }
  
  /**
   * Maps the property uom from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected UnitOfMeasureDto toDto_uom(final Quantity in, final MappingContext context) {
    if(in.getUom() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<UnitOfMeasureDto, UnitOfMeasure> mapper = (org.lunifera.dsl.dto.lib.IMapper<UnitOfMeasureDto, UnitOfMeasure>) getToDtoMapper(UnitOfMeasureDto.class, in.getUom().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	UnitOfMeasureDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getUom()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getUom(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getUom(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property uom from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected UnitOfMeasure toEntity_uom(final QuantityDto in, final MappingContext context) {
    if(in.getUom() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<UnitOfMeasureDto, UnitOfMeasure> mapper = (org.lunifera.dsl.dto.lib.IMapper<UnitOfMeasureDto, UnitOfMeasure>) getToEntityMapper(in.getUom().getClass(), UnitOfMeasure.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	UnitOfMeasure entity = null;
    	entity = context.get(mapper.createEntityHash(in.getUom()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getUom(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  public String createDtoHash(final Object in) {
    throw new UnsupportedOperationException("No id attribute available");
  }
  
  public String createEntityHash(final Object in) {
    throw new UnsupportedOperationException("No id attribute available");
  }
}
