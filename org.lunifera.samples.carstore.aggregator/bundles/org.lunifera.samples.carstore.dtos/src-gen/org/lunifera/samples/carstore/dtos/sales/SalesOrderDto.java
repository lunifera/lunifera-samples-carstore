package org.lunifera.samples.carstore.dtos.sales;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.BaseDto;
import org.lunifera.samples.carstore.dtos.general.CustomerDto;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;

@SuppressWarnings("all")
public class SalesOrderDto extends BaseDto implements IDto, Serializable, PropertyChangeListener {
  private String number;
  
  @DomainReference
  private CustomerDto customer;
  
  @DomainReference
  private PaymentTermDto paymentTerm;
  
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
  @Dispose
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
   * Returns the customer property or <code>null</code> if not present.
   */
  public CustomerDto getCustomer() {
    return this.customer;
  }
  
  /**
   * Sets the <code>customer</code> property to this instance.
   * 
   * @param customer - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCustomer(final CustomerDto customer) {
    checkDisposed();
    firePropertyChange("customer", this.customer, this.customer = customer);
  }
  
  /**
   * Returns the paymentTerm property or <code>null</code> if not present.
   */
  public PaymentTermDto getPaymentTerm() {
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
  
  public SalesOrderDto createDto() {
    return new SalesOrderDto();
  }
  
  public SalesOrderDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    SalesOrderDto newDto = context.get(this);
    if(newDto != null){
    	return newDto;
    }
    
    try{
    	context.increaseLevel();
    	
    	newDto = createDto();
    	context.register(this, newDto);
    	
    	// first copy the containments and attributes
    	copyContainments(this, newDto, context);
    	
    	// then copy cross references to ensure proper
    	// opposite references are copied too.
    	copyCrossReferences(this, newDto, context);
    } finally {
    	context.decreaseLevel();
    }
    
    return newDto;
  }
  
  public void copyContainments(final SalesOrderDto dto, final SalesOrderDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy number
    newDto.setNumber(getNumber());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final SalesOrderDto dto, final SalesOrderDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyCrossReferences(dto, newDto, context);
    
    // copy cross references (cascading is false)
    // copy dto customer
    if(getCustomer() != null) {
    	newDto.setCustomer(getCustomer().copy(context));
    }
    // copy dto paymentTerm
    if(getPaymentTerm() != null) {
    	newDto.setPaymentTerm(getPaymentTerm().copy(context));
    }
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    { 
    	super.propertyChange(event);
    }
  }
}
