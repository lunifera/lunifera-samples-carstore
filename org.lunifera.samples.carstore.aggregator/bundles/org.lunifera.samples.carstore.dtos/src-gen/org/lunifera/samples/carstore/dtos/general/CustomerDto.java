package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.AddressDto;
import org.lunifera.samples.carstore.dtos.general.BaseDto;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;

@SuppressWarnings("all")
public class CustomerDto extends BaseDto implements IDto, Serializable, PropertyChangeListener {
  private String number;
  
  private String name;
  
  @DomainReference
  private PaymentTermDto paymentTerms;
  
  private AddressDto deliveryAddress;
  
  private AddressDto invoiceAddress;
  
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
   * Returns the name property or <code>null</code> if not present.
   */
  public String getName() {
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
   * Returns the paymentTerms property or <code>null</code> if not present.
   */
  public PaymentTermDto getPaymentTerms() {
    return this.paymentTerms;
  }
  
  /**
   * Sets the <code>paymentTerms</code> property to this instance.
   * 
   * @param paymentTerms - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPaymentTerms(final PaymentTermDto paymentTerms) {
    checkDisposed();
    firePropertyChange("paymentTerms", this.paymentTerms, this.paymentTerms = paymentTerms);
  }
  
  /**
   * Returns the deliveryAddress property.
   */
  public AddressDto getDeliveryAddress() {
    if(this.deliveryAddress== null){
      this.deliveryAddress = new AddressDto();
    }
    return this.deliveryAddress;
  }
  
  /**
   * Sets the <code>deliveryAddress</code> property to this instance.
   * 
   * @param deliveryAddress - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setDeliveryAddress(final AddressDto deliveryAddress) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.deliveryAddress != null) {
    	this.deliveryAddress.removePropertyChangeListener(this);
    }
    
    firePropertyChange("deliveryAddress", this.deliveryAddress, this.deliveryAddress = deliveryAddress );
    
    if (this.deliveryAddress != null) {
    	this.deliveryAddress.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the invoiceAddress property.
   */
  public AddressDto getInvoiceAddress() {
    if(this.invoiceAddress== null){
      this.invoiceAddress = new AddressDto();
    }
    return this.invoiceAddress;
  }
  
  /**
   * Sets the <code>invoiceAddress</code> property to this instance.
   * 
   * @param invoiceAddress - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setInvoiceAddress(final AddressDto invoiceAddress) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.invoiceAddress != null) {
    	this.invoiceAddress.removePropertyChangeListener(this);
    }
    
    firePropertyChange("invoiceAddress", this.invoiceAddress, this.invoiceAddress = invoiceAddress );
    
    if (this.invoiceAddress != null) {
    	this.invoiceAddress.addPropertyChangeListener(this);
    }
  }
  
  public CustomerDto createDto() {
    return new CustomerDto();
  }
  
  public CustomerDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    CustomerDto newDto = context.get(this);
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
  
  public void copyContainments(final CustomerDto dto, final CustomerDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy number
    newDto.setNumber(getNumber());
    // copy name
    newDto.setName(getName());
    // copy dto deliveryAddress
    if(getDeliveryAddress() != null) {
    	newDto.setDeliveryAddress(getDeliveryAddress().copy(context));
    }
    // copy dto invoiceAddress
    if(getInvoiceAddress() != null) {
    	newDto.setInvoiceAddress(getInvoiceAddress().copy(context));
    }
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final CustomerDto dto, final CustomerDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyCrossReferences(dto, newDto, context);
    
    // copy cross references (cascading is false)
    // copy dto paymentTerms
    if(getPaymentTerms() != null) {
    	newDto.setPaymentTerms(getPaymentTerms().copy(context));
    }
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    if(source == deliveryAddress){
    	firePropertyChange("deliveryAddress" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    if(source == invoiceAddress){
    	firePropertyChange("invoiceAddress" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	super.propertyChange(event);
    }
  }
}
