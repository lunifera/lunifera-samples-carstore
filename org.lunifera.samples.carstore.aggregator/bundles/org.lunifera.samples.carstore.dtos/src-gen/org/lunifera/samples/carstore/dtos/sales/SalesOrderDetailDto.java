package org.lunifera.samples.carstore.dtos.sales;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.AmountDto;
import org.lunifera.samples.carstore.dtos.general.BaseDto;
import org.lunifera.samples.carstore.dtos.general.ItemDto;
import org.lunifera.samples.carstore.dtos.general.PaymentTermDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.dtos.general.QuantityDto;
import org.lunifera.samples.carstore.dtos.sales.CarConfigDetailDto;

@SuppressWarnings("all")
public class SalesOrderDetailDto extends BaseDto implements IDto, Serializable, PropertyChangeListener {
  private int number;
  
  @DomainReference
  private ItemDto item;
  
  @DomainReference
  private PaymentTermDto paymentTerm;
  
  private PriceDto price;
  
  private QuantityDto quantity;
  
  private AmountDto value;
  
  @DomainReference
  private List<CarConfigDetailDto> configDetails;
  
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
    try {
      // Dispose all the composition references.
      if (this.configDetails != null) {
        for (CarConfigDetailDto carConfigDetailDto : this.configDetails) {
          carConfigDetailDto.dispose();
        }
        this.configDetails = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
  }
  
  /**
   * Returns the number property or <code>null</code> if not present.
   */
  public int getNumber() {
    return this.number;
  }
  
  /**
   * Sets the <code>number</code> property to this instance.
   * 
   * @param number - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setNumber(final int number) {
    firePropertyChange("number", this.number, this.number = number );
  }
  
  /**
   * Returns the item property or <code>null</code> if not present.
   */
  public ItemDto getItem() {
    return this.item;
  }
  
  /**
   * Sets the <code>item</code> property to this instance.
   * 
   * @param item - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setItem(final ItemDto item) {
    checkDisposed();
    firePropertyChange("item", this.item, this.item = item);
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
  
  /**
   * Returns the price property.
   */
  public PriceDto getPrice() {
    if(this.price== null){
      this.price = new PriceDto();
    }
    return this.price;
  }
  
  /**
   * Sets the <code>price</code> property to this instance.
   * 
   * @param price - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPrice(final PriceDto price) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.price != null) {
    	this.price.removePropertyChangeListener(this);
    }
    
    firePropertyChange("price", this.price, this.price = price );
    
    if (this.price != null) {
    	this.price.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the quantity property.
   */
  public QuantityDto getQuantity() {
    if(this.quantity== null){
      this.quantity = new QuantityDto();
    }
    return this.quantity;
  }
  
  /**
   * Sets the <code>quantity</code> property to this instance.
   * 
   * @param quantity - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setQuantity(final QuantityDto quantity) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.quantity != null) {
    	this.quantity.removePropertyChangeListener(this);
    }
    
    firePropertyChange("quantity", this.quantity, this.quantity = quantity );
    
    if (this.quantity != null) {
    	this.quantity.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the value property.
   */
  public AmountDto getValue() {
    if(this.value== null){
      this.value = new AmountDto();
    }
    return this.value;
  }
  
  /**
   * Sets the <code>value</code> property to this instance.
   * 
   * @param value - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setValue(final AmountDto value) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.value != null) {
    	this.value.removePropertyChangeListener(this);
    }
    
    firePropertyChange("value", this.value, this.value = value );
    
    if (this.value != null) {
    	this.value.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns an unmodifiable list of configDetails.
   */
  public List<CarConfigDetailDto> getConfigDetails() {
    return Collections.unmodifiableList(internalGetConfigDetails());
  }
  
  /**
   * Returns the list of <code>CarConfigDetailDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<CarConfigDetailDto> internalGetConfigDetails() {
    if (this.configDetails == null) {
      this.configDetails = new java.util.ArrayList<CarConfigDetailDto>();
    }
    return this.configDetails;
  }
  
  /**
   * Adds the given carConfigDetailDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>CarConfigDetailDto#parent</code> of the <code>carConfigDetailDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link CarConfigDetailDto#setParent(CarConfigDetailDto)}.
   * 
   * @param carConfigDetailDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToConfigDetails(final CarConfigDetailDto carConfigDetailDto) {
    checkDisposed();
    
    carConfigDetailDto.setParent(this);
  }
  
  /**
   * Removes the given carConfigDetailDto from this object. <p>
   * 
   * @param carConfigDetailDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromConfigDetails(final CarConfigDetailDto carConfigDetailDto) {
    checkDisposed();
    
    carConfigDetailDto.setParent(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToConfigDetails(final CarConfigDetailDto carConfigDetailDto) {
    internalGetConfigDetails().add(carConfigDetailDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromConfigDetails(final CarConfigDetailDto carConfigDetailDto) {
    internalGetConfigDetails().remove(carConfigDetailDto);
  }
  
  /**
   * Sets the <code>configDetails</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>CarConfigDetailDto#
   * parent</code> of the <code>configDetails</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link CarConfigDetailDto#setParent(CarConfigDetailDto)
   * 
   * @param configDetails - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setConfigDetails(final List<CarConfigDetailDto> configDetails) {
    checkDisposed();
    for (CarConfigDetailDto dto : internalGetConfigDetails().toArray(new CarConfigDetailDto[this.configDetails.size()])) {
    	removeFromConfigDetails(dto);
    }
    
    if(configDetails == null) {
    	return;
    }
    
    for (CarConfigDetailDto dto : configDetails) {
    	addToConfigDetails(dto);
    }
  }
  
  public SalesOrderDetailDto createDto() {
    return new SalesOrderDetailDto();
  }
  
  public SalesOrderDetailDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    SalesOrderDetailDto newDto = context.get(this);
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
  
  public void copyContainments(final SalesOrderDetailDto dto, final SalesOrderDetailDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy number
    newDto.setNumber(getNumber());
    // copy dto price
    if(getPrice() != null) {
    	newDto.setPrice(getPrice().copy(context));
    }
    // copy dto quantity
    if(getQuantity() != null) {
    	newDto.setQuantity(getQuantity().copy(context));
    }
    // copy dto value
    if(getValue() != null) {
    	newDto.setValue(getValue().copy(context));
    }
    
    // copy containment references (cascading is true)
    // copy list of configDetails dtos
    for(org.lunifera.samples.carstore.dtos.sales.CarConfigDetailDto _dto : getConfigDetails()) {
    	newDto.addToConfigDetails(_dto.copy(context));
    }
  }
  
  public void copyCrossReferences(final SalesOrderDetailDto dto, final SalesOrderDetailDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyCrossReferences(dto, newDto, context);
    
    // copy cross references (cascading is false)
    // copy dto item
    if(getItem() != null) {
    	newDto.setItem(getItem().copy(context));
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
    if(source == price){
    	firePropertyChange("price" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    if(source == quantity){
    	firePropertyChange("quantity" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    if(source == value){
    	firePropertyChange("value" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	super.propertyChange(event);
    }
  }
}
