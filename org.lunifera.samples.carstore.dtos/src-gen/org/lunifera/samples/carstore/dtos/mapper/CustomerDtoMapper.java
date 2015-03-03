package org.lunifera.samples.carstore.dtos.mapper;

import java.util.List;
import org.lunifera.samples.carstore.Address;
import org.lunifera.samples.carstore.Customer;
import org.lunifera.samples.carstore.PaymentTerm;
import org.lunifera.samples.carstore.SalesOrder;
import org.lunifera.samples.carstore.dtos.AddressDto;
import org.lunifera.samples.carstore.dtos.CustomerDto;
import org.lunifera.samples.carstore.dtos.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.SalesOrderDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link CustomerDto} to and from the entity {@link Customer}.
 * 
 */
@SuppressWarnings("all")
public class CustomerDtoMapper<DTO extends CustomerDto, ENTITY extends Customer> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link Customer} to the dto {@link CustomerDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final CustomerDto dto, final Customer entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setNumber(toDto_number(entity));
    dto.setName(toDto_name(entity));
    dto.setAddress(toDto_address(entity));
    dto.setPaymentTerm(toDto_paymentTerm(entity));
    for(org.lunifera.samples.carstore.dtos.SalesOrderDto _dtoValue : toDto_orders(entity)) {
    	dto.addToOrders(_dtoValue);
    }
  }
  
  /**
   * Maps the dto {@link CustomerDto} to the entity {@link Customer}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final CustomerDto dto, final Customer entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setName(toEntity_name(dto));
    
    entity.setAddress(toEntity_address(dto));
    
    entity.setPaymentTerm(toEntity_paymentTerm(dto));
    
    List<SalesOrder> orders_entities = new java.util.ArrayList<SalesOrder>();
    for(SalesOrder _entityValue : toEntity_orders(dto)) {
    	orders_entities.add(_entityValue);
    }
    entity.setOrders(orders_entities);
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final Customer in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final CustomerDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_name(final Customer in) {
    return in.getName();
  }
  
  /**
   * Maps the property name from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_name(final CustomerDto in) {
    return in.getName();
  }
  
  /**
   * Maps the property address from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected AddressDto toDto_address(final Customer in) {
    org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = getMapper(AddressDto.class, Address.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
     
    if(in.getAddress() != null) {
    	AddressDto dto = new AddressDto();
    	mapper.mapToDTO(dto, in.getAddress());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property address from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected Address toEntity_address(final CustomerDto in) {
    org.lunifera.dsl.dto.lib.IMapper<AddressDto, Address> mapper = getMapper(AddressDto.class, Address.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getAddress() != null) {
    	Address entity = new Address();
    	mapper.mapToEntity(in.getAddress(), entity);
    	return entity;							
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property paymentTerm from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected PaymentTermDto toDto_paymentTerm(final Customer in) {
    org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm> mapper = getMapper(PaymentTermDto.class, PaymentTerm.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getPaymentTerm() != null) {
    	PaymentTermDto dto = new PaymentTermDto();
    	mapper.mapToDTO(dto, in.getPaymentTerm());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property paymentTerm from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected PaymentTerm toEntity_paymentTerm(final CustomerDto in) {
    org.lunifera.dsl.dto.lib.IMapper<PaymentTermDto, PaymentTerm> mapper = getMapper(PaymentTermDto.class, PaymentTerm.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getPaymentTerm() != null) {
    	PaymentTerm entity = new PaymentTerm();
    	mapper.mapToEntity(in.getPaymentTerm(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property orders from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return A list of mapped dtos
   * 
   */
  protected List<SalesOrderDto> toDto_orders(final Customer in) {
    org.lunifera.dsl.dto.lib.IMapper<SalesOrderDto, SalesOrder> mapper = getMapper(SalesOrderDto.class, SalesOrder.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<SalesOrderDto> results = new java.util.ArrayList<SalesOrderDto>();
    for (SalesOrder _entity : in.getOrders()) {
    	SalesOrderDto _dto = new SalesOrderDto();
    	mapper.mapToDTO(_dto, _entity);
    	results.add(_dto);
    }
    return results;
  }
  
  /**
   * Maps the property orders from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return A list of mapped entities
   * 
   */
  protected List<SalesOrder> toEntity_orders(final CustomerDto in) {
    org.lunifera.dsl.dto.lib.IMapper<SalesOrderDto, SalesOrder> mapper = getMapper(SalesOrderDto.class, SalesOrder.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<SalesOrder> results = new java.util.ArrayList<SalesOrder>();
    for (SalesOrderDto _dto : in.getOrders()) {
    	SalesOrder _entity = new SalesOrder();
    	mapper.mapToEntity(_dto, _entity);
    	results.add(_entity);
    }
    return results;
  }
}
