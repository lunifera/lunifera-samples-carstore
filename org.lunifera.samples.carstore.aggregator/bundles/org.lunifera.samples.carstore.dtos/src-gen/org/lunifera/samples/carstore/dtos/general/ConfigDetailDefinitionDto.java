package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.dtos.general.NumberedWithDescriptionDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;

@SuppressWarnings("all")
public class ConfigDetailDefinitionDto extends NumberedWithDescriptionDto implements IDto, Serializable, PropertyChangeListener {
  private PriceDto price;
  
  @DomainReference
  private CarDto car;
  
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
   * Returns the car property or <code>null</code> if not present.
   */
  public CarDto getCar() {
    return this.car;
  }
  
  /**
   * Sets the <code>car</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>CarDto#
   * configDetails</code> of the <code>car</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link CarDto#setConfigDetails(CarDto)
   * 
   * @param car - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCar(final CarDto car) {
    checkDisposed();
    if (this.car != null) {
    	this.car.internalRemoveFromConfigDetails(this);
    }
    
    internalSetCar(car);
    
    if (this.car != null) {
    	this.car.internalAddToConfigDetails(this);
    }
  }
  
  /**
   * For internal use only!
   */
  public void internalSetCar(final CarDto car) {
    firePropertyChange("car", this.car, this.car = car);
  }
  
  public ConfigDetailDefinitionDto createDto() {
    return new ConfigDetailDefinitionDto();
  }
  
  public ConfigDetailDefinitionDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    ConfigDetailDefinitionDto newDto = context.get(this);
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
  
  public void copyContainments(final ConfigDetailDefinitionDto dto, final ConfigDetailDefinitionDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy dto price
    if(getPrice() != null) {
    	newDto.setPrice(getPrice().copy(context));
    }
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final ConfigDetailDefinitionDto dto, final ConfigDetailDefinitionDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyCrossReferences(dto, newDto, context);
    
    // copy cross references (cascading is false)
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    if(source == price){
    	firePropertyChange("price" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	super.propertyChange(event);
    }
  }
}
