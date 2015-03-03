package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Currency;
import org.lunifera.samples.carstore.dtos.CurrencyDto;

@SuppressWarnings("all")
public class CurrencyService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
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
