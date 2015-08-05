package org.lunifera.samples.carstore.dtos.general.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOServiceWithMutablePersistence;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;

@SuppressWarnings("all")
public class PaymentTermService extends AbstractDTOServiceWithMutablePersistence<PaymentTermDto, PaymentTerm> {
  public PaymentTermService() {
    // set the default persistence ID
    setPersistenceId("carstore");
  }
  
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
