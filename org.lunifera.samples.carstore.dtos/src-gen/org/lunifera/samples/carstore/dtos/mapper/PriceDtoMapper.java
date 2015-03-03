package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.samples.carstore.Price;
import org.lunifera.samples.carstore.dtos.PriceDto;

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
  protected <D, E> IMapper<D, E> getMapper(final Class<D> dtoClass, final Class<E> entityClass) {
    return mapperAccess.getMapper(dtoClass, entityClass);
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
   * Maps the entity {@link Price} to the dto {@link PriceDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final PriceDto dto, final Price entity) {
    
    dto.setAmount(toDto_amount(entity));
    dto.setCurrency(toDto_currency(entity));
  }
  
  /**
   * Maps the dto {@link PriceDto} to the entity {@link Price}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final PriceDto dto, final Price entity) {
    
    entity.setAmount(toEntity_amount(dto));
    
    entity.setCurrency(toEntity_currency(dto));
    
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toDto_amount(final Price in) {
    return in.getAmount();
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toEntity_amount(final PriceDto in) {
    return in.getAmount();
  }
  
  /**
   * Maps the property currency from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_currency(final Price in) {
    return in.getCurrency();
  }
  
  /**
   * Maps the property currency from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_currency(final PriceDto in) {
    return in.getCurrency();
  }
}
