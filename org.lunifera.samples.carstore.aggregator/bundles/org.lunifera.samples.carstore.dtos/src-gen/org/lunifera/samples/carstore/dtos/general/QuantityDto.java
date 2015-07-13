package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.runtime.common.annotations.TargetEnumConstraint;
import org.lunifera.runtime.common.annotations.TargetEnumConstraints;
import org.lunifera.samples.carstore.dtos.general.UOMFamily;
import org.lunifera.samples.carstore.dtos.general.UnitOfMeasureDto;

@SuppressWarnings("all")
public class QuantityDto implements IDto, Serializable, PropertyChangeListener {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  @Dispose
  private boolean disposed;
  
  private float amount;
  
  @DomainReference
  @TargetEnumConstraints(constraints = @TargetEnumConstraint(targetProperty = "family", enumClass = UOMFamily.class, enumLiteral = "QUANTITY"))
  private UnitOfMeasureDto uom;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  public boolean isDisposed() {
    return this.disposed;
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(PropertyChangeListener)
   */
  public void addPropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#addPropertyChangeListener(String, PropertyChangeListener)
   */
  public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(PropertyChangeListener)
   */
  public void removePropertyChangeListener(final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }
  
  /**
   * @see PropertyChangeSupport#removePropertyChangeListener(String, PropertyChangeListener)
   */
  public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
    propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
  }
  
  /**
   * @see PropertyChangeSupport#firePropertyChange(String, Object, Object)
   */
  public void firePropertyChange(final String propertyName, final Object oldValue, final Object newValue) {
    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
  }
  
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
    firePropertyChange("disposed", this.disposed, this.disposed = true);
  }
  
  /**
   * Returns the amount property or <code>null</code> if not present.
   */
  public float getAmount() {
    return this.amount;
  }
  
  /**
   * Sets the <code>amount</code> property to this instance.
   * 
   * @param amount - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setAmount(final float amount) {
    firePropertyChange("amount", this.amount, this.amount = amount );
  }
  
  /**
   * Returns the uom property or <code>null</code> if not present.
   */
  public UnitOfMeasureDto getUom() {
    return this.uom;
  }
  
  /**
   * Sets the <code>uom</code> property to this instance.
   * 
   * @param uom - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setUom(final UnitOfMeasureDto uom) {
    checkDisposed();
    firePropertyChange("uom", this.uom, this.uom = uom);
  }
  
  public QuantityDto createDto() {
    return new QuantityDto();
  }
  
  public QuantityDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    QuantityDto newDto = context.get(this);
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
  
  public void copyContainments(final QuantityDto dto, final QuantityDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy attributes and beans (beans if derived from entity model)
    // copy amount
    newDto.setAmount(getAmount());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final QuantityDto dto, final QuantityDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy cross references (cascading is false)
    // copy dto uom
    if(getUom() != null) {
    	newDto.setUom(getUom().copy(context));
    }
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    { 
    	// no super class available to forward event
    }
  }
}
