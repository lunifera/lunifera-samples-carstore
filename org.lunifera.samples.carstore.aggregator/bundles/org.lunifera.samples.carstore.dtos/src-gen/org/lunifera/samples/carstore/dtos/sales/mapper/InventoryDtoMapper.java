package org.lunifera.samples.carstore.dtos.sales.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.ItemDto;
import org.lunifera.samples.carstore.dtos.general.QuantityDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.dtos.sales.InventoryDto;
import org.lunifera.samples.carstore.entities.general.Item;
import org.lunifera.samples.carstore.entities.general.Quantity;
import org.lunifera.samples.carstore.entities.sales.Inventory;

/**
 * This class maps the dto {@link InventoryDto} to and from the entity {@link Inventory}.
 * 
 */
@SuppressWarnings("all")
public class InventoryDtoMapper<DTO extends InventoryDto, ENTITY extends Inventory> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Inventory createEntity() {
    return new Inventory();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public InventoryDto createDto() {
    return new InventoryDto();
  }
  
  /**
   * Maps the entity {@link Inventory} to the dto {@link InventoryDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final InventoryDto dto, final Inventory entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setItem(toDto_item(entity, context));
    dto.setRequestedQuantity(toDto_requestedQuantity(entity, context));
    dto.setCountedQuantity(toDto_countedQuantity(entity, context));
  }
  
  /**
   * Maps the dto {@link InventoryDto} to the entity {@link Inventory}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final InventoryDto dto, final Inventory entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setItem(toEntity_item(dto, context));
    
    entity.setRequestedQuantity(toEntity_requestedQuantity(dto, context));
    
    entity.setCountedQuantity(toEntity_countedQuantity(dto, context));
    
  }
  
  /**
   * Maps the property item from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected ItemDto toDto_item(final Inventory in, final MappingContext context) {
    if(in.getItem() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ItemDto, Item> mapper = (org.lunifera.dsl.dto.lib.IMapper<ItemDto, Item>) getToDtoMapper(ItemDto.class, in.getItem().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	ItemDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getItem()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getItem(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getItem(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property item from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected Item toEntity_item(final InventoryDto in, final MappingContext context) {
    if(in.getItem() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ItemDto, Item> mapper = (org.lunifera.dsl.dto.lib.IMapper<ItemDto, Item>) getToEntityMapper(in.getItem().getClass(), Item.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Item entity = null;
    	entity = context.get(mapper.createEntityHash(in.getItem()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getItem(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property requestedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected QuantityDto toDto_requestedQuantity(final Inventory in, final MappingContext context) {
    if(in.getRequestedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToDtoMapper(QuantityDto.class, in.getRequestedQuantity().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	QuantityDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getRequestedQuantity(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property requestedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Quantity toEntity_requestedQuantity(final InventoryDto in, final MappingContext context) {
    if(in.getRequestedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToEntityMapper(in.getRequestedQuantity().getClass(), Quantity.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Quantity entity = mapper.createEntity();
    	mapper.mapToEntity(in.getRequestedQuantity(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property countedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected QuantityDto toDto_countedQuantity(final Inventory in, final MappingContext context) {
    if(in.getCountedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToDtoMapper(QuantityDto.class, in.getCountedQuantity().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	QuantityDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getCountedQuantity(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property countedQuantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Quantity toEntity_countedQuantity(final InventoryDto in, final MappingContext context) {
    if(in.getCountedQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToEntityMapper(in.getCountedQuantity().getClass(), Quantity.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Quantity entity = mapper.createEntity();
    	mapper.mapToEntity(in.getCountedQuantity(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(InventoryDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Inventory.class, in);
  }
}
