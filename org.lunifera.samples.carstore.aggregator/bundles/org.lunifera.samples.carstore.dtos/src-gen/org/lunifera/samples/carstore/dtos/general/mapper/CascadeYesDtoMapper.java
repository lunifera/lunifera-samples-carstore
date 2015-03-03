package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CascadeRootDto;
import org.lunifera.samples.carstore.dtos.general.CascadeYesDto;
import org.lunifera.samples.carstore.entities.general.CascadeRoot;
import org.lunifera.samples.carstore.entities.general.CascadeYes;

/**
 * This class maps the dto {@link CascadeYesDto} to and from the entity {@link CascadeYes}.
 * 
 */
@SuppressWarnings("all")
public class CascadeYesDtoMapper<DTO extends CascadeYesDto, ENTITY extends CascadeYes> implements IMapper<DTO, ENTITY> {
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
  public CascadeYes createEntity() {
    return new CascadeYes();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CascadeYesDto createDto() {
    return new CascadeYesDto();
  }
  
  /**
   * Maps the entity {@link CascadeYes} to the dto {@link CascadeYesDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CascadeYesDto dto, final CascadeYes entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    
    dto.setId(toDto_id(entity, context));
  }
  
  /**
   * Maps the dto {@link CascadeYesDto} to the entity {@link CascadeYes}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CascadeYesDto dto, final CascadeYes entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
    entity.setId(toEntity_id(dto, context));
    
    
  }
  
  /**
   * Maps the property id from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_id(final CascadeYes in, final MappingContext context) {
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
  protected String toEntity_id(final CascadeYesDto in, final MappingContext context) {
    return in.getId();
  }
  
  /**
   * Maps the property root from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected CascadeRootDto toDto_root(final CascadeYes in, final MappingContext context) {
    if(in.getRoot() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CascadeRootDto, CascadeRoot> mapper = (org.lunifera.dsl.dto.lib.IMapper<CascadeRootDto, CascadeRoot>) getToDtoMapper(CascadeRootDto.class, in.getRoot().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	CascadeRootDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getRoot()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getRoot(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getRoot(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property root from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected CascadeRoot toEntity_root(final CascadeYesDto in, final MappingContext context) {
    if(in.getRoot() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CascadeRootDto, CascadeRoot> mapper = (org.lunifera.dsl.dto.lib.IMapper<CascadeRootDto, CascadeRoot>) getToEntityMapper(in.getRoot().getClass(), CascadeRoot.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	CascadeRoot entity = null;
    	entity = context.get(mapper.createEntityHash(in.getRoot()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getRoot(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CascadeYesDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CascadeYes.class, in);
  }
}
