package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CurrencyDto;
import org.lunifera.samples.carstore.dtos.general.mapper.NumberedWithDescriptionDtoMapper;
import org.lunifera.samples.carstore.entities.general.Currency;

/**
 * This class maps the dto {@link CurrencyDto} to and from the entity {@link Currency}.
 * 
 */
@SuppressWarnings("all")
public class CurrencyDtoMapper<DTO extends CurrencyDto, ENTITY extends Currency> extends NumberedWithDescriptionDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Currency createEntity() {
    return new Currency();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CurrencyDto createDto() {
    return new CurrencyDto();
  }
  
  /**
   * Maps the entity {@link Currency} to the dto {@link CurrencyDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CurrencyDto dto, final Currency entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setIso3Code(toDto_iso3Code(entity, context));
  }
  
  /**
   * Maps the dto {@link CurrencyDto} to the entity {@link Currency}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CurrencyDto dto, final Currency entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    entity.setIso3Code(toEntity_iso3Code(dto, context));
  }
  
  /**
   * Maps the property iso3Code from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_iso3Code(final Currency in, final MappingContext context) {
    return in.getIso3Code();
  }
  
  /**
   * Maps the property iso3Code from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_iso3Code(final CurrencyDto in, final MappingContext context) {
    return in.getIso3Code();
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CurrencyDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Currency.class, in);
  }
}
