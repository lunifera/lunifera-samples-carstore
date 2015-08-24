package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.CurrencyDto;
import org.lunifera.samples.carstore.entities.general.Currency;

@SuppressWarnings("all")
public class CurrencyDtoService extends AbstractDTOServiceWithMutablePersistence<CurrencyDto, Currency> {
  public CurrencyDtoService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
  public Class<CurrencyDto> getDtoClass() {
    return CurrencyDto.class;
  }
  
  public CurrencyDto createDto() {
    return new CurrencyDto();
  }
  
  public Currency createEntity() {
    return new Currency();
  }
  
  public Class<Currency> getEntityClass() {
    return Currency.class;
  }
  
  public Object getId(final CurrencyDto dto) {
    return dto.getId();
  }
}
