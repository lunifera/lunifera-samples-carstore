package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.samples.carstore.Address;
import org.lunifera.samples.carstore.dtos.AddressDto;

/**
 * This class maps the dto {@link AddressDto} to and from the entity {@link Address}.
 * 
 */
@SuppressWarnings("all")
public class AddressDtoMapper<DTO extends AddressDto, ENTITY extends Address> implements IMapper<DTO, ENTITY> {
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
   * Maps the entity {@link Address} to the dto {@link AddressDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final AddressDto dto, final Address entity) {
    
    dto.setStreet(toDto_street(entity));
    dto.setPostalcode(toDto_postalcode(entity));
  }
  
  /**
   * Maps the dto {@link AddressDto} to the entity {@link Address}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final AddressDto dto, final Address entity) {
    
    entity.setStreet(toEntity_street(dto));
    
    entity.setPostalcode(toEntity_postalcode(dto));
    
  }
  
  /**
   * Maps the property street from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_street(final Address in) {
    return in.getStreet();
  }
  
  /**
   * Maps the property street from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_street(final AddressDto in) {
    return in.getStreet();
  }
  
  /**
   * Maps the property postalcode from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_postalcode(final Address in) {
    return in.getPostalcode();
  }
  
  /**
   * Maps the property postalcode from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_postalcode(final AddressDto in) {
    return in.getPostalcode();
  }
}
