package org.lunifera.samples.carstore.dtos.general.mobile;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.dtos.general.WeightDto;
import org.lunifera.samples.carstore.dtos.general.mobile.ItemMobileDto;

@SuppressWarnings("all")
public class CarMobileDto extends ItemMobileDto implements Serializable, PropertyChangeListener {
  private int axes;
  
  private WeightDto weight;
  
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
   * Returns the axes property or <code>null</code> if not present.
   */
  public int getAxes() {
    return this.axes;
  }
  
  /**
   * Sets the <code>axes</code> property to this instance.
   * 
   * @param axes - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setAxes(final int axes) {
    firePropertyChange("axes", this.axes, this.axes = axes );
  }
  
  /**
   * Returns the weight property.
   */
  public WeightDto getWeight() {
    if(this.weight== null){
      this.weight = new WeightDto();
    }
    return this.weight;
  }
  
  /**
   * Sets the <code>weight</code> property to this instance.
   * 
   * @param weight - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setWeight(final WeightDto weight) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.weight != null) {
    	this.weight.removePropertyChangeListener(this);
    }
    
    firePropertyChange("weight", this.weight, this.weight = weight );
    
    if (this.weight != null) {
    	this.weight.addPropertyChangeListener(this);
    }
  }
  
  public CarMobileDto createDto() {
    return new CarMobileDto();
  }
  
  public CarMobileDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    CarMobileDto newDto = context.get(this);
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
  
  public void copyContainments(final CarMobileDto dto, final CarMobileDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy axes
    newDto.setAxes(getAxes());
    // copy dto weight
    if(getWeight() != null) {
    	newDto.setWeight(getWeight().copy(context));
    }
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final CarMobileDto dto, final CarMobileDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
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
    if(source == weight){
    	firePropertyChange("weight" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	super.propertyChange(event);
    }
  }
}
