package org.lunifera.samples.carstore.dtos.mapper;

import java.util.List;
import org.lunifera.samples.carstore.Currency;
import org.lunifera.samples.carstore.Customer;
import org.lunifera.samples.carstore.SODetail;
import org.lunifera.samples.carstore.SalesOrder;
import org.lunifera.samples.carstore.dtos.CurrencyDto;
import org.lunifera.samples.carstore.dtos.CustomerDto;
import org.lunifera.samples.carstore.dtos.SODetailDto;
import org.lunifera.samples.carstore.dtos.SalesOrderDto;
import org.lunifera.samples.carstore.dtos.mapper.BaseUUIDDtoMapper;

/**
 * This class maps the dto {@link SalesOrderDto} to and from the entity {@link SalesOrder}.
 * 
 */
@SuppressWarnings("all")
public class SalesOrderDtoMapper<DTO extends SalesOrderDto, ENTITY extends SalesOrder> extends BaseUUIDDtoMapper<DTO, ENTITY> {
  /**
   * Maps the entity {@link SalesOrder} to the dto {@link SalesOrderDto}.
   * 
   * @param dto - The target dto
   * @param entity - The source entity
   * 
   */
  public void mapToDTO(final SalesOrderDto dto, final SalesOrder entity) {
    super.mapToDTO(dto, entity);
    
    
    dto.setNumber(toDto_number(entity));
    dto.setCurrency(toDto_currency(entity));
    dto.setCustomer(toDto_customer(entity));
    for(org.lunifera.samples.carstore.dtos.SODetailDto _dtoValue : toDto_soDetails(entity)) {
    	dto.addToSoDetails(_dtoValue);
    }
  }
  
  /**
   * Maps the dto {@link SalesOrderDto} to the entity {@link SalesOrder}.
   * 
   * @param dto - The source dto
   * @param entity - The target entity
   * 
   */
  public void mapToEntity(final SalesOrderDto dto, final SalesOrder entity) {
    super.mapToEntity(dto, entity);
    
    
    entity.setNumber(toEntity_number(dto));
    
    entity.setCurrency(toEntity_currency(dto));
    
    entity.setCustomer(toEntity_customer(dto));
    
    List<SODetail> soDetails_entities = new java.util.ArrayList<SODetail>();
    for(SODetail _entityValue : toEntity_soDetails(dto)) {
    	soDetails_entities.add(_entityValue);
    }
    entity.setSoDetails(soDetails_entities);
    
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toDto_number(final SalesOrder in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property number from the given entity to dto property.
   * 
   * @param in - The source entity
   * @return the mapped value
   * 
   */
  protected String toEntity_number(final SalesOrderDto in) {
    return in.getNumber();
  }
  
  /**
   * Maps the property currency from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected CurrencyDto toDto_currency(final SalesOrder in) {
    org.lunifera.dsl.dto.lib.IMapper<CurrencyDto, Currency> mapper = getMapper(CurrencyDto.class, Currency.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getCurrency() != null) {
    	CurrencyDto dto = new CurrencyDto();
    	mapper.mapToDTO(dto, in.getCurrency());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property currency from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected Currency toEntity_currency(final SalesOrderDto in) {
    org.lunifera.dsl.dto.lib.IMapper<CurrencyDto, Currency> mapper = getMapper(CurrencyDto.class, Currency.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getCurrency() != null) {
    	Currency entity = new Currency();
    	mapper.mapToEntity(in.getCurrency(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property customer from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return the mapped dto
   * 
   */
  protected CustomerDto toDto_customer(final SalesOrder in) {
    org.lunifera.dsl.dto.lib.IMapper<CustomerDto, Customer> mapper = getMapper(CustomerDto.class, Customer.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getCustomer() != null) {
    	CustomerDto dto = new CustomerDto();
    	mapper.mapToDTO(dto, in.getCustomer());
    	return dto;
    } else {
    	return null;
    }
  }
  
  /**
   * Maps the property customer from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return the mapped entity
   * 
   */
  protected Customer toEntity_customer(final SalesOrderDto in) {
    org.lunifera.dsl.dto.lib.IMapper<CustomerDto, Customer> mapper = getMapper(CustomerDto.class, Customer.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    if(in.getCustomer() != null) {
    	Customer entity = new Customer();
    	mapper.mapToEntity(in.getCustomer(), entity);	
    	return entity;
    } else {
    	return null;
    }	
  }
  
  /**
   * Maps the property soDetails from the given entity to the dto.
   * 
   * @param in - The source entity
   * @return A list of mapped dtos
   * 
   */
  protected List<SODetailDto> toDto_soDetails(final SalesOrder in) {
    org.lunifera.dsl.dto.lib.IMapper<SODetailDto, SODetail> mapper = getMapper(SODetailDto.class, SODetail.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    } 
    
    List<SODetailDto> results = new java.util.ArrayList<SODetailDto>();
    for (SODetail _entity : in.getSoDetails()) {
    	SODetailDto _dto = new SODetailDto();
    	mapper.mapToDTO(_dto, _entity);
    	results.add(_dto);
    }
    return results;
  }
  
  /**
   * Maps the property soDetails from the given dto to the entity.
   * 
   * @param in - The source dto
   * @return A list of mapped entities
   * 
   */
  protected List<SODetail> toEntity_soDetails(final SalesOrderDto in) {
    org.lunifera.dsl.dto.lib.IMapper<SODetailDto, SODetail> mapper = getMapper(SODetailDto.class, SODetail.class);
    if(mapper == null) {
    	throw new IllegalStateException("Mapper must not be null!");
    }
    
    List<SODetail> results = new java.util.ArrayList<SODetail>();
    for (SODetailDto _dto : in.getSoDetails()) {
    	SODetail _entity = new SODetail();
    	mapper.mapToEntity(_dto, _entity);
    	results.add(_entity);
    }
    return results;
  }
}
