package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.samples.carstore.SITypeEnum;
import org.lunifera.samples.carstore.Weight;
import org.lunifera.samples.carstore.dtos.WeightDto;

/**
 * This class maps the dto {@link WeightDto} to and from the entity {@link Weight}.
 * 
 */
@SuppressWarnings("all")
public class WeightDtoMapper<DTO extends WeightDto, ENTITY extends Weight> implements IMapper<DTO, ENTITY> {
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
   * Maps the entity {@link Weight} to the dto {@link WeightDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final WeightDto dto, final Weight entity) {
    
    dto.setAmount(toDto_amount(entity));
    dto.setSiType(toDto_siType(entity));
  }
  
  /**
   * Maps the dto {@link WeightDto} to the entity {@link Weight}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final WeightDto dto, final Weight entity) {
    
    entity.setAmount(toEntity_amount(dto));
    
    entity.setSiType(toEntity_siType(dto));
    
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toDto_amount(final Weight in) {
    return in.getAmount();
  }
  
  /**
   * Maps the property amount from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected int toEntity_amount(final WeightDto in) {
    return in.getAmount();
  }
  
  /**
   * Maps the property siType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected SITypeEnum toDto_siType(final Weight in) {
    return in.getSiType();
  }
  
  /**
   * Maps the property siType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected SITypeEnum toEntity_siType(final WeightDto in) {
    return in.getSiType();
  }
}
