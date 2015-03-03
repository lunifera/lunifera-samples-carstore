package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.SiType;
import org.lunifera.samples.carstore.dtos.general.UOMFamily;
import org.lunifera.samples.carstore.dtos.general.UnitOfMeasureDto;
import org.lunifera.samples.carstore.dtos.general.mapper.NumberedWithDescriptionDtoMapper;
import org.lunifera.samples.carstore.entities.general.UnitOfMeasure;

/**
 * This class maps the dto {@link UnitOfMeasureDto} to and from the entity {@link UnitOfMeasure}.
 * 
 */
@SuppressWarnings("all")
public class UnitOfMeasureDtoMapper<DTO extends UnitOfMeasureDto, ENTITY extends UnitOfMeasure> extends NumberedWithDescriptionDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public UnitOfMeasure createEntity() {
    return new UnitOfMeasure();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public UnitOfMeasureDto createDto() {
    return new UnitOfMeasureDto();
  }
  
  /**
   * Maps the entity {@link UnitOfMeasure} to the dto {@link UnitOfMeasureDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final UnitOfMeasureDto dto, final UnitOfMeasure entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setSiType(toDto_siType(entity, context));
    dto.setFamily(toDto_family(entity, context));
  }
  
  /**
   * Maps the dto {@link UnitOfMeasureDto} to the entity {@link UnitOfMeasure}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final UnitOfMeasureDto dto, final UnitOfMeasure entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setSiType(toEntity_siType(dto, context));
    
    entity.setFamily(toEntity_family(dto, context));
    
  }
  
  /**
   * Maps the property siType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected SiType toDto_siType(final UnitOfMeasure in, final MappingContext context) {
    if(in.getSiType() != null) {
    	return org.lunifera.samples.carstore.dtos.general.SiType.valueOf(in.getSiType().name());
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property siType from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected org.lunifera.samples.carstore.entities.general.SiType toEntity_siType(final UnitOfMeasureDto in, final MappingContext context) {
    if(in.getSiType() != null) {
    	return org.lunifera.samples.carstore.entities.general.SiType.valueOf(in.getSiType().name());
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property family from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected UOMFamily toDto_family(final UnitOfMeasure in, final MappingContext context) {
    if(in.getFamily() != null) {
    	return org.lunifera.samples.carstore.dtos.general.UOMFamily.valueOf(in.getFamily().name());
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property family from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected org.lunifera.samples.carstore.entities.general.UOMFamily toEntity_family(final UnitOfMeasureDto in, final MappingContext context) {
    if(in.getFamily() != null) {
    	return org.lunifera.samples.carstore.entities.general.UOMFamily.valueOf(in.getFamily().name());
    } else {
    	return null;
    }
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(UnitOfMeasureDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(UnitOfMeasure.class, in);
  }
}
