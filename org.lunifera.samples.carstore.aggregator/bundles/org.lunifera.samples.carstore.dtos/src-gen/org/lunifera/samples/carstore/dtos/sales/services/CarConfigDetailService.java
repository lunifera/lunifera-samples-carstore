package org.lunifera.samples.carstore.dtos.sales.services;

import javax.persistence.EntityManagerFactory;

@SuppressWarnings("all")
public class CarConfigDetailService {
  private EntityManagerFactory emf;
  
  /**
   * Binds the service {@link javax.persistence.EntityManagerFactory} to this component. 
   * <br>The cardinality is ONE_TO_ONE
   * 
   * @param emf the service
   */
  protected void bindEmf(final EntityManagerFactory emf) {
    this.emf = emf;
  }
  
  /**
   * Unbinds the service from this component. 
   * <br>The cardinality is ONE_TO_ONE
   * 
   * @param emf the service
   */
  protected void unbindEmf(final EntityManagerFactory emf) {
    this.emf = null;
  }
}
