package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.AddressDto;
import org.lunifera.samples.carstore.entities.general.Address;

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
  public Address createEntity() {
    return new Address();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public AddressDto createDto() {
    return new AddressDto();
  }
  
  /**
   * Maps the entity {@link Address} to the dto {@link AddressDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final AddressDto dto, final Address entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    dto.setStreet(toDto_street(entity, context));
    dto.setPostalCode(toDto_postalCode(entity, context));
  }
  
  /**
   * Maps the dto {@link AddressDto} to the entity {@link Address}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final AddressDto dto, final Address entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    
    entity.setStreet(toEntity_street(dto, context));
    entity.setPostalCode(toEntity_postalCode(dto, context));
  }
  
  /**
   * Maps the property street from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_street(final Address in, final MappingContext context) {
    return in.getStreet();
  }
  
  /**
   * Maps the property street from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_street(final AddressDto in, final MappingContext context) {
    return in.getStreet();
  }
  
  /**
   * Maps the property postalCode from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_postalCode(final Address in, final MappingContext context) {
    return in.getPostalCode();
  }
  
  /**
   * Maps the property postalCode from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_postalCode(final AddressDto in, final MappingContext context) {
    return in.getPostalCode();
  }
  
  public String createDtoHash(final Object in) {
    throw new UnsupportedOperationException("No id attribute available");
  }
  
  public String createEntityHash(final Object in) {
    throw new UnsupportedOperationException("No id attribute available");
  }
}
