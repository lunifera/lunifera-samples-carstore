package org.lunifera.samples.carstore.dtos.general.mapper;

import java.util.List;
import org.lunifera.dsl.dto.lib.IMapper;
import org.lunifera.dsl.dto.lib.IMapperAccess;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CascadeNoDto;
import org.lunifera.samples.carstore.dtos.general.CascadeRootDto;
import org.lunifera.samples.carstore.dtos.general.CascadeYesDto;
import org.lunifera.samples.carstore.entities.general.CascadeNo;
import org.lunifera.samples.carstore.entities.general.CascadeRoot;
import org.lunifera.samples.carstore.entities.general.CascadeYes;

/**
 * This class maps the dto {@link CascadeRootDto} to and from the entity {@link CascadeRoot}.
 * 
 */
@SuppressWarnings("all")
public class CascadeRootDtoMapper<DTO extends CascadeRootDto, ENTITY extends CascadeRoot> implements IMapper<DTO, ENTITY> {
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
  public CascadeRoot createEntity() {
    return new CascadeRoot();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CascadeRootDto createDto() {
    return new CascadeRootDto();
  }
  
  /**
   * Maps the entity {@link CascadeRoot} to the dto {@link CascadeRootDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CascadeRootDto dto, final CascadeRoot entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    dto.setId(toDto_id(entity, context));
    if(dto.getRefYes().isEmpty()) {
    	for(org.lunifera.samples.carstore.dtos.general.CascadeYesDto _dtoValue : toDto_refYes(entity, context)) {
    		dto.addToRefYes(_dtoValue);
    	}
    }
    if(dto.getRefNo().isEmpty()) {
    	for(org.lunifera.samples.carstore.dtos.general.CascadeNoDto _dtoValue : toDto_refNo(entity, context)) {
    		dto.addToRefNo(_dtoValue);
    	}
    }
  }
  
  /**
   * Maps the dto {@link CascadeRootDto} to the entity {@link CascadeRoot}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CascadeRootDto dto, final CascadeRoot entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    
    entity.setId(toEntity_id(dto, context));
    if(entity.getRefYes().isEmpty()) {
    	List<CascadeYes> refYes_entities = new java.util.ArrayList<CascadeYes>();
    	for(CascadeYes _entityValue : toEntity_refYes(dto, context)) {
    		refYes_entities.add(_entityValue);
    	}
    	entity.setRefYes(refYes_entities);
    }
    if(entity.getRefNo().isEmpty()) {
    	List<CascadeNo> refNo_entities = new java.util.ArrayList<CascadeNo>();
    	for(CascadeNo _entityValue : toEntity_refNo(dto, context)) {
    		refNo_entities.add(_entityValue);
    	}
    	entity.setRefNo(refNo_entities);
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
  protected String toDto_id(final CascadeRoot in, final MappingContext context) {
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
  protected String toEntity_id(final CascadeRootDto in, final MappingContext context) {
    return in.getId();
  }
  
  /**
   * Maps the property refYes from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return A list of mapped dtos
   * 
   */
  protected List<CascadeYesDto> toDto_refYes(final CascadeRoot in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<CascadeYesDto, CascadeYes> mapper = getToDtoMapper(CascadeYesDto.class, CascadeYes.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<CascadeYesDto> results = new java.util.ArrayList<CascadeYesDto>();
    for (CascadeYes _entity : in.getRefYes()) {
    	CascadeYesDto _dto = context.get(mapper.createDtoHash(_entity));
    	if (_dto == null) {
    		_dto = mapper.createDto();
    		mapper.mapToDTO(_dto, _entity, context);
    	} else {
    		if(context.isRefresh()){
    			mapper.mapToDTO(_dto, _entity, context);
    		}
    	}
    	results.add(_dto);
    }
    return results;
  }
  
  /**
   * Maps the property refYes from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return A list of mapped entities
   * 
   */
  protected List<CascadeYes> toEntity_refYes(final CascadeRootDto in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<CascadeYesDto, CascadeYes> mapper = getToEntityMapper(CascadeYesDto.class, CascadeYes.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<CascadeYes> results = new java.util.ArrayList<CascadeYes>();
    for (CascadeYesDto _dto : in.getRefYes()) {
    	CascadeYes _entity = context.get(mapper.createEntityHash(_dto));
    	if(_entity == null) {
    		_entity = mapper.createEntity();
    		mapper.mapToEntity(_dto, _entity, context);
    	}
    	results.add(_entity);
    }
    return results;
  }
  
  /**
   * Maps the property refNo from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return A list of mapped dtos
   * 
   */
  protected List<CascadeNoDto> toDto_refNo(final CascadeRoot in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<CascadeNoDto, CascadeNo> mapper = getToDtoMapper(CascadeNoDto.class, CascadeNo.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<CascadeNoDto> results = new java.util.ArrayList<CascadeNoDto>();
    for (CascadeNo _entity : in.getRefNo()) {
    	CascadeNoDto _dto = context.get(mapper.createDtoHash(_entity));
    	if (_dto == null) {
    		_dto = mapper.createDto();
    		mapper.mapToDTO(_dto, _entity, context);
    	} else {
    		if(context.isRefresh()){
    			mapper.mapToDTO(_dto, _entity, context);
    		}
    	}
    	results.add(_dto);
    }
    return results;
  }
  
  /**
   * Maps the property refNo from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return A list of mapped entities
   * 
   */
  protected List<CascadeNo> toEntity_refNo(final CascadeRootDto in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<CascadeNoDto, CascadeNo> mapper = getToEntityMapper(CascadeNoDto.class, CascadeNo.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<CascadeNo> results = new java.util.ArrayList<CascadeNo>();
    for (CascadeNoDto _dto : in.getRefNo()) {
    	CascadeNo _entity = context.get(mapper.createEntityHash(_dto));
    	if(_entity == null) {
    		_entity = mapper.createEntity();
    		mapper.mapToEntity(_dto, _entity, context);
    	}
    	results.add(_entity);
    }
    return results;
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CascadeRootDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CascadeRoot.class, in);
  }
}
