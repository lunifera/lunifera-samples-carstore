package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;

/**
 * This class maps the dto {@link BaseUUIDDto} to and from the entity {@link BaseUUID}.
 * 
 */
@SuppressWarnings("all")
public class BaseUUIDDtoMapper<DTO extends BaseUUIDDto, ENTITY extends BaseUUID> implements IMapper<DTO, ENTITY> {
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
   * Maps the entity {@link BaseUUID} to the dto {@link BaseUUIDDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final BaseUUIDDto dto, final BaseUUID entity) {
    
    dto.setId(toDto_id(entity));
  }
  
  /**
   * Maps the dto {@link BaseUUIDDto} to the entity {@link BaseUUID}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final BaseUUIDDto dto, final BaseUUID entity) {
    
    entity.setId(toEntity_id(dto));
    
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_id(final BaseUUID in) {
    return in.getId();
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_id(final BaseUUIDDto in) {
    return in.getId();
  }
}
