package org.lunifera.samples.carstore.dtos.mapper;

import org.lunifera.samples.carstore.Item;
import org.lunifera.samples.carstore.SODetail;
import org.lunifera.samples.carstore.SalesOrder;
import org.lunifera.samples.carstore.dtos.ItemDto;
import org.lunifera.samples.carstore.dtos.SODetailDto;
import org.lunifera.samples.carstore.dtos.SalesOrderDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link SODetailDto} to and from the entity {@link SODetail}.
 * 
 */
@SuppressWarnings("all")
public class SODetailDtoMapper<DTO extends SODetailDto, ENTITY extends SODetail> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link SODetail} to the dto {@link SODetailDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final SODetailDto dto, final SODetail entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setOrder(toDto_order(entity));
    dto.setItem(toDto_item(entity));
  }
  
  /**
   * Maps the dto {@link SODetailDto} to the entity {@link SODetail}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final SODetailDto dto, final SODetail entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setOrder(toEntity_order(dto));
    
    entity.setItem(toEntity_item(dto));
    
  }
  
  /**
   * Maps the property order from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected SalesOrderDto toDto_order(final SODetail in) {
    org.lunifera.dsl.dto.lib.IMapper<SalesOrderDto, SalesOrder> mapper = getMapper(SalesOrderDto.class, SalesOrder.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getOrder() != null) {
    	SalesOrderDto dto = new SalesOrderDto();
    	mapper.mapToDTO(dto, in.getOrder());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property order from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected SalesOrder toEntity_order(final SODetailDto in) {
    org.lunifera.dsl.dto.lib.IMapper<SalesOrderDto, SalesOrder> mapper = getMapper(SalesOrderDto.class, SalesOrder.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getOrder() != null) {
    	SalesOrder entity = new SalesOrder();
    	mapper.mapToEntity(in.getOrder(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property item from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected ItemDto toDto_item(final SODetail in) {
    org.lunifera.dsl.dto.lib.IMapper<ItemDto, Item> mapper = getMapper(ItemDto.class, Item.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getItem() != null) {
    	ItemDto dto = new ItemDto();
    	mapper.mapToDTO(dto, in.getItem());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property item from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected Item toEntity_item(final SODetailDto in) {
    org.lunifera.dsl.dto.lib.IMapper<ItemDto, Item> mapper = getMapper(ItemDto.class, Item.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getItem() != null) {
    	Item entity = new Item();
    	mapper.mapToEntity(in.getItem(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
}
