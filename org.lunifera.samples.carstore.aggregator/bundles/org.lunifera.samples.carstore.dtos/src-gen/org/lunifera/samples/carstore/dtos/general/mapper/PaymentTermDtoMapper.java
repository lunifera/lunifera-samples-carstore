package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.general.mapper.NumberedWithDescriptionDtoMapper;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;

/**
 * This class maps the dto {@link PaymentTermDto} to and from the entity {@link PaymentTerm}.
 * 
 */
@SuppressWarnings("all")
public class PaymentTermDtoMapper<DTO extends PaymentTermDto, ENTITY extends PaymentTerm> extends NumberedWithDescriptionDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public PaymentTerm createEntity() {
    return new PaymentTerm();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public PaymentTermDto createDto() {
    return new PaymentTermDto();
  }
  
  /**
   * Maps the entity {@link PaymentTerm} to the dto {@link PaymentTermDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final PaymentTermDto dto, final PaymentTerm entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
  }
  
  /**
   * Maps the dto {@link PaymentTermDto} to the entity {@link PaymentTerm}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final PaymentTermDto dto, final PaymentTerm entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(PaymentTermDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(PaymentTerm.class, in);
  }
}
