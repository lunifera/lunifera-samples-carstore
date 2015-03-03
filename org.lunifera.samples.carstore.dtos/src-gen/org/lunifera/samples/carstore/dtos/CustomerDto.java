package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.AddressDto;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;
import org.lunifera.samples.carstore.dtos.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.SalesOrderDto;

@SuppressWarnings("all")
public class CustomerDto extends BaseUUIDDto implements Serializable {
  private String number;
  
  private String name;
  
  private AddressDto address;
  
  @DomainReference
  private PaymentTermDto paymentTerm;
  
  @DomainReference
  private List<SalesOrderDto> orders;
  
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
    super.dispose();
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
   * Returns the name property or <code>null</code> if not present.
   */
  public String getName() {
    checkDisposed();
    return this.name;
  }
  
  /**
   * Sets the <code>name</code> property to this instance.
   * 
   * @param name - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setName(final String name) {
    firePropertyChange("name", this.name, this.name = name );
  }
  
  /**
   * Returns the address property or <code>null</code> if not present.
   */
  public AddressDto getAddress() {
    checkDisposed();
    return this.address;
  }
  
  /**
   * Sets the <code>address</code> property to this instance.
   * 
   * @param address - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setAddress(final AddressDto address) {
    firePropertyChange("address", this.address, this.address = address );
  }
  
  /**
   * Returns the paymentTerm property or <code>null</code> if not present.
   */
  public PaymentTermDto getPaymentTerm() {
    checkDisposed();
    return this.paymentTerm;
  }
  
  /**
   * Sets the <code>paymentTerm</code> property to this instance.
   * 
   * @param paymentTerm - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPaymentTerm(final PaymentTermDto paymentTerm) {
    checkDisposed();
    firePropertyChange("paymentTerm", this.paymentTerm, this.paymentTerm = paymentTerm);
  }
  
  /**
   * Returns an unmodifiable list of orders.
   */
  public List<SalesOrderDto> getOrders() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetOrders());
  }
  
  /**
   * Returns the list of <code>SalesOrderDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<SalesOrderDto> internalGetOrders() {
    if (this.orders == null) {
      this.orders = new java.util.ArrayList<SalesOrderDto>();
    }
    return this.orders;
  }
  
  /**
   * Adds the given salesOrderDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>SalesOrderDto#customer</code> of the <code>salesOrderDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link SalesOrderDto#setCustomer(SalesOrderDto)}.
   * 
   * @param salesOrderDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToOrders(final SalesOrderDto salesOrderDto) {
    checkDisposed();
    salesOrderDto.setCustomer(this);
  }
  
  /**
   * Removes the given salesOrderDto from this object. <p>
   * 
   * @param salesOrderDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromOrders(final SalesOrderDto salesOrderDto) {
    checkDisposed();
    salesOrderDto.setCustomer(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToOrders(final SalesOrderDto salesOrderDto) {
    internalGetOrders().add(salesOrderDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromOrders(final SalesOrderDto salesOrderDto) {
    internalGetOrders().remove(salesOrderDto);
  }
  
  /**
   * Sets the <code>orders</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>SalesOrderDto#
   * customer</code> of the <code>orders</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link SalesOrderDto#setCustomer(SalesOrderDto)
   * 
   * @param orders - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setOrders(final List<SalesOrderDto> orders) {
    checkDisposed();
    for (SalesOrderDto dto : internalGetOrders().toArray(new SalesOrderDto[this.orders.size()])) {
      removeFromOrders(dto);
    }
    if(orders == null){
      return;
    }
    for (SalesOrderDto dto : orders) {
      addToOrders(dto);
    }
    
  }
}
