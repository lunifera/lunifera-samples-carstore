package org.lunifera.samples.carstore.dtos.sales.mobile.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.dtos.general.mobile.ItemMobileDto;
import org.lunifera.samples.carstore.dtos.sales.mobile.SalesOrderDetailMobileDto;
import org.lunifera.samples.carstore.entities.general.Item;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

/**
 * This class maps the dto {@link SalesOrderDetailMobileDto} to and from the entity {@link SalesOrderDetail}.
 * 
 */
@SuppressWarnings("all")
public class SalesOrderDetailMobileDtoMapper<DTO extends SalesOrderDetailMobileDto, ENTITY extends SalesOrderDetail> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public SalesOrderDetail createEntity() {
    return new SalesOrderDetail();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public SalesOrderDetailMobileDto createDto() {
    return new SalesOrderDetailMobileDto();
  }
  
  /**
   * Maps the entity {@link SalesOrderDetail} to the dto {@link SalesOrderDetailMobileDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final SalesOrderDetailMobileDto dto, final SalesOrderDetail entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setItem(toDto_item(entity, context));
    dto.setPaymentTerm(toDto_paymentTerm(entity, context));
  }
  
  /**
   * Maps the dto {@link SalesOrderDetailMobileDto} to the entity {@link SalesOrderDetail}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final SalesOrderDetailMobileDto dto, final SalesOrderDetail entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setNumber(toEntity_number(dto, context));
    
    entity.setItem(toEntity_item(dto, context));
    
    entity.setPaymentTerm(toEntity_paymentTerm(dto, context));
    
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
  protected int toEntity_number(final SalesOrderDetailMobileDto in, final MappingContext context) {
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
  protected ItemMobileDto toDto_item(final SalesOrderDetail in, final MappingContext context) {
    if(in.getItem() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ItemMobileDto, Item> mapper = (org.lunifera.dsl.dto.lib.IMapper<ItemMobileDto, Item>) getToDtoMapper(ItemMobileDto.class, in.getItem().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	ItemMobileDto dto = null;
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
  protected Item toEntity_item(final SalesOrderDetailMobileDto in, final MappingContext context) {
    if(in.getItem() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<ItemMobileDto, Item> mapper = (org.lunifera.dsl.dto.lib.IMapper<ItemMobileDto, Item>) getToEntityMapper(in.getItem().getClass(), Item.class);
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
  protected PaymentTerm toEntity_paymentTerm(final SalesOrderDetailMobileDto in, final MappingContext context) {
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
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(SalesOrderDetailMobileDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(SalesOrderDetail.class, in);
  }
}
