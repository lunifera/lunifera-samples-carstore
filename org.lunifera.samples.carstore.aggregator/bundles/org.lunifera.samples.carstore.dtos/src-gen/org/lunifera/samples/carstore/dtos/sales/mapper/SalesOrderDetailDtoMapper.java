package org.lunifera.samples.carstore.dtos.sales.mapper;

import java.util.List;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.AmountDto;
import org.lunifera.samples.carstore.dtos.general.ItemDto;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.dtos.general.QuantityDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.dtos.sales.CarConfigDetailDto;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;
import org.lunifera.samples.carstore.entities.general.Amount;
import org.lunifera.samples.carstore.entities.general.Item;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.Quantity;
import org.lunifera.samples.carstore.entities.sales.CarConfigDetail;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

/**
 * This class maps the dto {@link SalesOrderDetailDto} to and from the entity {@link SalesOrderDetail}.
 * 
 */
@SuppressWarnings("all")
public class SalesOrderDetailDtoMapper<DTO extends SalesOrderDetailDto, ENTITY extends SalesOrderDetail> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public SalesOrderDetail createEntity() {
    return new SalesOrderDetail();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public SalesOrderDetailDto createDto() {
    return new SalesOrderDetailDto();
  }
  
  /**
   * Maps the entity {@link SalesOrderDetail} to the dto {@link SalesOrderDetailDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final SalesOrderDetailDto dto, final SalesOrderDetail entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setItem(toDto_item(entity, context));
    dto.setPaymentTerm(toDto_paymentTerm(entity, context));
    dto.setPrice(toDto_price(entity, context));
    dto.setQuantity(toDto_quantity(entity, context));
    dto.setValue(toDto_value(entity, context));
    for(org.lunifera.samples.carstore.dtos.sales.CarConfigDetailDto _dtoValue : toDto_configDetails(entity, context)) {
    	dto.addToConfigDetails(_dtoValue);
    }
  }
  
  /**
   * Maps the dto {@link SalesOrderDetailDto} to the entity {@link SalesOrderDetail}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final SalesOrderDetailDto dto, final SalesOrderDetail entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setNumber(toEntity_number(dto, context));
    
    entity.setItem(toEntity_item(dto, context));
    
    entity.setPaymentTerm(toEntity_paymentTerm(dto, context));
    
    entity.setPrice(toEntity_price(dto, context));
    
    entity.setQuantity(toEntity_quantity(dto, context));
    
    entity.setValue(toEntity_value(dto, context));
    
    List<CarConfigDetail> configDetails_entities = new java.util.ArrayList<CarConfigDetail>();
    for(CarConfigDetail _entityValue : toEntity_configDetails(dto, context)) {
    	configDetails_entities.add(_entityValue);
    }
    entity.setConfigDetails(configDetails_entities);
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toDto_number(final SalesOrderDetail in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected int toEntity_number(final SalesOrderDetailDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property item from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected ItemDto toDto_item(final SalesOrderDetail in, final MappingContext context) {
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
  protected Item toEntity_item(final SalesOrderDetailDto in, final MappingContext context) {
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
   * Maps the property paymentTerm from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected PaymentTermDto toDto_paymentTerm(final SalesOrderDetail in, final MappingContext context) {
    if(in.getPaymentTerm() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm> mapper = (org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm>) getToDtoMapper(PaymentTermDto.class, in.getPaymentTerm().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	PaymentTermDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getPaymentTerm()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getPaymentTerm(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getPaymentTerm(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property paymentTerm from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected PaymentTerm toEntity_paymentTerm(final SalesOrderDetailDto in, final MappingContext context) {
    if(in.getPaymentTerm() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm> mapper = (org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm>) getToEntityMapper(in.getPaymentTerm().getClass(), PaymentTerm.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	PaymentTerm entity = null;
    	entity = context.get(mapper.createEntityHash(in.getPaymentTerm()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getPaymentTerm(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected PriceDto toDto_price(final SalesOrderDetail in, final MappingContext context) {
    if(in.getPrice() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price> mapper = (org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price>) getToDtoMapper(PriceDto.class, in.getPrice().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	PriceDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getPrice(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property price from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Price toEntity_price(final SalesOrderDetailDto in, final MappingContext context) {
    if(in.getPrice() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price> mapper = (org.lunifera.dsl.dto.lib.IMapper<PriceDto, Price>) getToEntityMapper(in.getPrice().getClass(), Price.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Price entity = mapper.createEntity();
    	mapper.mapToEntity(in.getPrice(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property quantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected QuantityDto toDto_quantity(final SalesOrderDetail in, final MappingContext context) {
    if(in.getQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToDtoMapper(QuantityDto.class, in.getQuantity().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	QuantityDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getQuantity(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property quantity from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Quantity toEntity_quantity(final SalesOrderDetailDto in, final MappingContext context) {
    if(in.getQuantity() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity> mapper = (org.lunifera.dsl.dto.lib.IMapper<QuantityDto, Quantity>) getToEntityMapper(in.getQuantity().getClass(), Quantity.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Quantity entity = mapper.createEntity();
    	mapper.mapToEntity(in.getQuantity(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property value from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected AmountDto toDto_value(final SalesOrderDetail in, final MappingContext context) {
    if(in.getValue() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<AmountDto, Amount> mapper = (org.lunifera.dsl.dto.lib.IMapper<AmountDto, Amount>) getToDtoMapper(AmountDto.class, in.getValue().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	AmountDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getValue(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property value from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Amount toEntity_value(final SalesOrderDetailDto in, final MappingContext context) {
    if(in.getValue() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<AmountDto, Amount> mapper = (org.lunifera.dsl.dto.lib.IMapper<AmountDto, Amount>) getToEntityMapper(in.getValue().getClass(), Amount.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Amount entity = mapper.createEntity();
    	mapper.mapToEntity(in.getValue(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property configDetails from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return A list of mapped dtos
   * 
   */
  protected List<CarConfigDetailDto> toDto_configDetails(final SalesOrderDetail in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<CarConfigDetailDto, CarConfigDetail> mapper = getToDtoMapper(CarConfigDetailDto.class, CarConfigDetail.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<CarConfigDetailDto> results = new java.util.ArrayList<CarConfigDetailDto>();
    for (CarConfigDetail _entity : in.getConfigDetails()) {
    	CarConfigDetailDto _dto = context.get(mapper.createDtoHash(_entity));
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
   * Maps the property configDetails from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return A list of mapped entities
   * 
   */
  protected List<CarConfigDetail> toEntity_configDetails(final SalesOrderDetailDto in, final MappingContext context) {
    org.lunifera.dsl.dto.lib.IMapper<CarConfigDetailDto, CarConfigDetail> mapper = getToEntityMapper(CarConfigDetailDto.class, CarConfigDetail.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<CarConfigDetail> results = new java.util.ArrayList<CarConfigDetail>();
    for (CarConfigDetailDto _dto : in.getConfigDetails()) {
    	CarConfigDetail _entity = context.get(mapper.createEntityHash(_dto));
    	if(_entity == null) {
    		_entity = mapper.createEntity();
    		mapper.mapToEntity(_dto, _entity, context);
    	}
    	results.add(_entity);
    }
    return results;
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(SalesOrderDetailDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(SalesOrderDetail.class, in);
  }
}
