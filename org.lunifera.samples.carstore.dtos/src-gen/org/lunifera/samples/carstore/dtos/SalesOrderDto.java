package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;
import org.lunifera.samples.carstore.dtos.CurrencyDto;
import org.lunifera.samples.carstore.dtos.CustomerDto;
import org.lunifera.samples.carstore.dtos.SODetailDto;

@SuppressWarnings("all")
public class SalesOrderDto extends BaseUUIDDto implements Serializable {
  private String number;
  
  @DomainReference
  private CurrencyDto currency;
  
  @DomainReference
  private CustomerDto customer;
  
  @DomainReference
  private List<SODetailDto> soDetails;
  
  /**
   * Checks whether the object is disposed.
   * @throws RuntimeException if the object is disposed.
   */
  private void checkDisposed() {
    if (isDisposed()) {
      throw new RuntimeException("Object already disposed: " + this);
    }
  }
  
  /**
   * Calling dispose will destroy that instance. The internal state will be 
   * set to 'disposed' and methods of that object must not be used anymore. 
   * Each call will result in runtime exceptions.<br/>
   * If this object keeps composition containments, these will be disposed too. 
   * So the whole composition containment tree will be disposed on calling this method.
   */
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    try {
      // Dispose all the composition references.
      if (this.soDetails != null) {
        for (SODetailDto sODetailDto : this.soDetails) {
          sODetailDto.dispose();
        }
        this.soDetails = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
  }
  
  /**
   * Returns the number property or <code>null</code> if not present.
   */
  public String getNumber() {
    checkDisposed();
    return this.number;
  }
  
  /**
   * Sets the <code>number</code> property to this instance.
   * 
   * @param number - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setNumber(final String number) {
    firePropertyChange("number", this.number, this.number = number );
  }
  
  /**
   * Returns the currency property or <code>null</code> if not present.
   */
  public CurrencyDto getCurrency() {
    checkDisposed();
    return this.currency;
  }
  
  /**
   * Sets the <code>currency</code> property to this instance.
   * 
   * @param currency - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCurrency(final CurrencyDto currency) {
    checkDisposed();
    firePropertyChange("currency", this.currency, this.currency = currency);
  }
  
  /**
   * Returns the <em>required</em> customer property.
   */
  public CustomerDto getCustomer() {
    checkDisposed();
    return this.customer;
  }
  
  /**
   * Sets the <code>customer</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>CustomerDto#
   * orders</code> of the <code>customer</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link CustomerDto#setOrders(CustomerDto)
   * 
   * @param customer - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCustomer(final CustomerDto customer) {
    checkDisposed();
    if (this.customer != null) {
      this.customer.internalRemoveFromOrders(this);
    }
    internalSetCustomer(customer);
    if (this.customer != null) {
      this.customer.internalAddToOrders(this);
    }
    
  }
  
  /**
   * For internal use only!
   */
  public void internalSetCustomer(final CustomerDto customer) {
    firePropertyChange("customer", this.customer, this.customer = customer);
  }
  
  /**
   * Returns an unmodifiable list of soDetails.
   */
  public List<SODetailDto> getSoDetails() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetSoDetails());
  }
  
  /**
   * Returns the list of <code>SODetailDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<SODetailDto> internalGetSoDetails() {
    if (this.soDetails == null) {
      this.soDetails = new java.util.ArrayList<SODetailDto>();
    }
    return this.soDetails;
  }
  
  /**
   * Adds the given sODetailDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>SODetailDto#order</code> of the <code>sODetailDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link SODetailDto#setOrder(SODetailDto)}.
   * 
   * @param sODetailDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToSoDetails(final SODetailDto sODetailDto) {
    checkDisposed();
    sODetailDto.setOrder(this);
  }
  
  /**
   * Removes the given sODetailDto from this object. <p>
   * 
   * @param sODetailDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromSoDetails(final SODetailDto sODetailDto) {
    checkDisposed();
    sODetailDto.setOrder(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToSoDetails(final SODetailDto sODetailDto) {
    internalGetSoDetails().add(sODetailDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromSoDetails(final SODetailDto sODetailDto) {
    internalGetSoDetails().remove(sODetailDto);
  }
  
  /**
   * Sets the <code>soDetails</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>SODetailDto#
   * order</code> of the <code>soDetails</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link SODetailDto#setOrder(SODetailDto)
   * 
   * @param soDetails - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setSoDetails(final List<SODetailDto> soDetails) {
    checkDisposed();
    for (SODetailDto dto : internalGetSoDetails().toArray(new SODetailDto[this.soDetails.size()])) {
      removeFromSoDetails(dto);
    }
    if(soDetails == null){
      return;
    }
    for (SODetailDto dto : soDetails) {
      addToSoDetails(dto);
    }
    
  }
}
