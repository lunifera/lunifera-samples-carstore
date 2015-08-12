package org.lunifera.samples.carstore.dtos.general.mapper;

import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.samples.carstore.dtos.general.AddressDto;
import org.lunifera.samples.carstore.dtos.general.CustomerDto;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.general.mapper.BaseDtoMapper;
import org.lunifera.samples.carstore.entities.general.Address;
import org.lunifera.samples.carstore.entities.general.Customer;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;

/**
 * This class maps the dto {@link CustomerDto} to and from the entity {@link Customer}.
 * 
 */
@SuppressWarnings("all")
public class CustomerDtoMapper<DTO extends CustomerDto, ENTITY extends Customer> extends BaseDtoMapper<DTO, ENTITY> {
  /**
   * Creates a new instance of the entity
   */
  public Customer createEntity() {
    return new Customer();
  }
  
  /**
   * Creates a new instance of the dto
   */
  public CustomerDto createDto() {
    return new CustomerDto();
  }
  
  /**
   * Maps the entity {@link Customer} to the dto {@link CustomerDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToDTO(final CustomerDto dto, final Customer entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    context.register(createDtoHash(entity), dto);
    
    super.mapToDTO(dto, entity, context);
    
    dto.setNumber(toDto_number(entity, context));
    dto.setName(toDto_name(entity, context));
    dto.setPaymentTerms(toDto_paymentTerms(entity, context));
    dto.setDeliveryAddress(toDto_deliveryAddress(entity, context));
    dto.setInvoiceAddress(toDto_invoiceAddress(entity, context));
  }
  
  /**
   * Maps the dto {@link CustomerDto} to the entity {@link Customer}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * @param context - The context to get information about depth,...
   * 
   */
  public void mapToEntity(final CustomerDto dto, final Customer entity, final MappingContext context) {
    if(context == null){
    	throw new IllegalArgumentException("Please pass a context!");
    }
    
    context.register(createEntityHash(dto), entity);
    context.registerMappingRoot(createEntityHash(dto), dto);
    super.mapToEntity(dto, entity, context);
    
    entity.setNumber(toEntity_number(dto, context));
    entity.setName(toEntity_name(dto, context));
    entity.setPaymentTerms(toEntity_paymentTerms(dto, context));
    entity.setDeliveryAddress(toEntity_deliveryAddress(dto, context));
    entity.setInvoiceAddress(toEntity_invoiceAddress(dto, context));
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_number(final Customer in, final MappingContext context) {
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
  protected String toEntity_number(final CustomerDto in, final MappingContext context) {
    return in.getNumber();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toDto_name(final Customer in, final MappingContext context) {
    return in.getName();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected String toEntity_name(final CustomerDto in, final MappingContext context) {
    return in.getName();
  }
  
  /**
   * Maps the property paymentTerms from the given entity to the dto.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped dto
   * 
   */
  protected PaymentTermDto toDto_paymentTerms(final Customer in, final MappingContext context) {
    if(in.getPaymentTerms() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm> mapper = (org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm>) getToDtoMapper(PaymentTermDto.class, in.getPaymentTerms().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    	PaymentTermDto dto = null;
    	dto = context.get(mapper.createDtoHash(in.getPaymentTerms()));
    	if(dto != null) {
    		if(context.isRefresh()){
    			mapper.mapToDTO(dto, in.getPaymentTerms(), context);
    		}
    		return dto;
    	}
    	
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getPaymentTerms(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property paymentTerms from the given dto to the entity.
   * 
   * @param in - The source dto
   * @param context - The context to get information about depth,...
   * @return the mapped entity
   * 
   */
  protected PaymentTerm toEntity_paymentTerms(final CustomerDto in, final MappingContext context) {
    if(in.getPaymentTerms() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm> mapper = (org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm>) getToEntityMapper(in.getPaymentTerms().getClass(), PaymentTerm.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	PaymentTerm entity = null;
    	entity = context.get(mapper.createEntityHash(in.getPaymentTerms()));
    	if(entity != null) {
    		return entity;
    	}
    
    	entity = mapper.createEntity();
    	mapper.mapToEntity(in.getPaymentTerms(), entity, context);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property deliveryAddress from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected AddressDto toDto_deliveryAddress(final Customer in, final MappingContext context) {
    if(in.getDeliveryAddress() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = (org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address>) getToDtoMapper(AddressDto.class, in.getDeliveryAddress().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	AddressDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getDeliveryAddress(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property deliveryAddress from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Address toEntity_deliveryAddress(final CustomerDto in, final MappingContext context) {
    if(in.getDeliveryAddress() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = (org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address>) getToEntityMapper(in.getDeliveryAddress().getClass(), Address.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Address entity = mapper.createEntity();
    	mapper.mapToEntity(in.getDeliveryAddress(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property invoiceAddress from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected AddressDto toDto_invoiceAddress(final Customer in, final MappingContext context) {
    if(in.getInvoiceAddress() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = (org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address>) getToDtoMapper(AddressDto.class, in.getInvoiceAddress().getClass());
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	AddressDto dto = null;
    	dto = mapper.createDto();
    	mapper.mapToDTO(dto, in.getInvoiceAddress(), context);
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property invoiceAddress from the given entity to dto property.
   * 
   * @param in - The source entity
   * @param context - The context to get information about depth,...
   * @return the mapped value
   * 
   */
  protected Address toEntity_invoiceAddress(final CustomerDto in, final MappingContext context) {
    if(in.getInvoiceAddress() != null) {
    	// find a mapper that knows how to map the concrete input type.
    	org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = (org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address>) getToEntityMapper(in.getInvoiceAddress().getClass(), Address.class);
    	if(mapper == null) {
    		throw new IllegalStateException("Mapper must not be null!");
    	}
    
    	Address entity = mapper.createEntity();
    	mapper.mapToEntity(in.getInvoiceAddress(), entity, context);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  public String createDtoHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(CustomerDto.class, in);
  }
  
  public String createEntityHash(final Object in) {
    return org.lunifera.runtime.common.hash.HashUtil.createObjectWithIdHash(Customer.class, in);
  }
}
