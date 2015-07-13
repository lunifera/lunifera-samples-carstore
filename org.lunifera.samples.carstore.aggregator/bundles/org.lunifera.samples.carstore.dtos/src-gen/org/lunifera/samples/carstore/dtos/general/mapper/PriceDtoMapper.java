package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CurrencyDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.entities.general.Currency;
import org.lunifera.samples.carstore.entities.general.Price;

/**
 * This class maps the dto {@link PriceDto} to and from the entity {@link Price}.
 * 
 */
@SuppressWarnings("all")
public class PriceDtoMapper<DTO extends PriceDto, ENTITY extends Price> implements IMapper<DTO, ENTITY> {
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
  public Price createEntity() {
    return new Price();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public PriceDto createDto() {
    return new PriceDto();
  }
  
  /**
   * Maps the entity {@link Price} to the dto {@link PriceDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final PriceDto dto, final Price entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    dto.setAmount(toDto_amount(entity, context));
    dto.setCurrency(toDto_currency(entity, context));
  }
  
  /**
   * Maps the dto {@link PriceDto} to the entity {@link Price}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final PriceDto dto, final Price entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    
    entity.setAmount(toEntity_amount(dto, context));
    entity.setCurrency(toEntity_currency(dto, context));
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected float toDto_amount(final Price in, final MappingContext context) {
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
  protected float toEntity_amount(final PriceDto in, final MappingContext context) {
    return in.getAmount();
  }
  
  /**
   * Maps the property currency from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected CurrencyDto toDto_currency(final Price in, final MappingContext context) {
    if(in.getCurrency() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CurrencyDto, Currency> mapper = (org.lunifera.dsl.dto.lib.IMapper<CurrencyDto, Currency>) getToDtoMapper(CurrencyDto.class, in.getCurrency().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	CurrencyDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getCurrency()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getCurrency(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getCurrency(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property currency from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected Currency toEntity_currency(final PriceDto in, final MappingContext context) {
    if(in.getCurrency() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CurrencyDto, Currency> mapper = (org.lunifera.dsl.dto.lib.IMapper<CurrencyDto, Currency>) getToEntityMapper(in.getCurrency().getClass(), Currency.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Currency entity = null;
    	entity = context.get(mapper.createEntityHash(in.getCurrency()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getCurrency(), entity, context);	
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
