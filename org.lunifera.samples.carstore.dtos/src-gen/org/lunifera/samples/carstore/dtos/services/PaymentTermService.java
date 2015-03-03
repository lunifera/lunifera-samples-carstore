package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.PaymentTerm;
import org.lunifera.samples.carstore.dtos.PaymentTermDto;

@SuppressWarnings("all")
public class PaymentTermService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<PaymentTermDto> getDtoClass() {
    return PaymentTermDto.class;
  }
  
  public PaymentTermDto createDto() {
    return new PaymentTermDto();
  }
  
  public PaymentTerm createEntity() {
    return new PaymentTerm();
  }
  
  public Class<PaymentTerm> getEntityClass() {
    return PaymentTerm.class;
  }
  
  public Object getId(final PaymentTermDto dto) {
    return dto.getId();
  }
}
