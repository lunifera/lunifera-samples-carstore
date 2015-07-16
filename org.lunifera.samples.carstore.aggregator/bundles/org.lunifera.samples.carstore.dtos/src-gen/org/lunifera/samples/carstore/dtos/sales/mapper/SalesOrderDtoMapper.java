package org.lunifera.samples.carstore.dtos.sales.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.CustomerDto;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDto;
import org.lunifera.samples.carstore.entities.general.Customer;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;
import org.lunifera.samples.carstore.entities.sales.SalesOrder;

/**
 * This class maps the dto {@link SalesOrderDto} to and from the entity {@link SalesOrder}.
 * 
 */
@SuppressWarnings("all")
public class SalesOrderDtoMapper<DTO extends SalesOrderDto, ENTITY extends SalesOrder> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public SalesOrder createEntity() {
    return new SalesOrder();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public SalesOrderDto createDto() {
    return new SalesOrderDto();
  }
  
  /**
   * Maps the entity {@link SalesOrder} to the dto {@link SalesOrderDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final SalesOrderDto dto, final SalesOrder entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setCustomer(toDto_customer(entity, context));
    dto.setPaymentTerm(toDto_paymentTerm(entity, context));
  }
  
  /**
   * Maps the dto {@link SalesOrderDto} to the entity {@link SalesOrder}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final SalesOrderDto dto, final SalesOrder entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    
    entity.setNumber(toEntity_number(dto, context));
    
    entity.setCustomer(toEntity_customer(dto, context));
    
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
  protected String toDto_number(final SalesOrder in, final MappingContext context) {
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
  protected String toEntity_number(final SalesOrderDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property customer from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected CustomerDto toDto_customer(final SalesOrder in, final MappingContext context) {
    if(in.getCustomer() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CustomerDto, Customer> mapper = (org.lunifera.dsl.dto.lib.IMapper<CustomerDto, Customer>) getToDtoMapper(CustomerDto.class, in.getCustomer().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	CustomerDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getCustomer()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getCustomer(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getCustomer(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property customer from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected Customer toEntity_customer(final SalesOrderDto in, final MappingContext context) {
    if(in.getCustomer() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<CustomerDto, Customer> mapper = (org.lunifera.dsl.dto.lib.IMapper<CustomerDto, Customer>) getToEntityMapper(in.getCustomer().getClass(), Customer.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Customer entity = null;
    	entity = context.get(mapper.createEntityHash(in.getCustomer()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getCustomer(), entity, context);	
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
  protected PaymentTermDto toDto_paymentTerm(final SalesOrder in, final MappingContext context) {
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
  protected PaymentTerm toEntity_paymentTerm(final SalesOrderDto in, final MappingContext context) {
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
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(SalesOrderDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(SalesOrder.class, in);
  }
}
