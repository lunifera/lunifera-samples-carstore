package org.lunifera.samples.carstore.dtos.general.mobile.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.mobile.PickupMobileDto;
import org.lunifera.samples.carstore.dtos.general.mobile.mapper.CarMobileDtoMapper;
import org.lunifera.samples.carstore.entities.general.Pickup;

/**
 * This class maps the dto {@link PickupMobileDto} to and from the entity {@link Pickup}.
 * 
 */
@SuppressWarnings("all")
public class PickupMobileDtoMapper<DTO extends PickupMobileDto, ENTITY extends Pickup> extends CarMobileDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Pickup createEntity() {
    return new Pickup();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public PickupMobileDto createDto() {
    return new PickupMobileDto();
  }
  
  /**
   * Maps the entity {@link Pickup} to the dto {@link PickupMobileDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final PickupMobileDto dto, final Pickup entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
  }
  
  /**
   * Maps the dto {@link PickupMobileDto} to the entity {@link Pickup}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final PickupMobileDto dto, final Pickup entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(PickupMobileDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Pickup.class, in);
  }
}
