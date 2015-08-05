package org.lunifera.samples.carstore.dtos.sales;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.dtos.general.QuantityDto;

@SuppressWarnings("all")
public class InventoryDto implements IDto, Serializable, PropertyChangeListener {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  @Dispose
  private boolean disposed;
  
  private QuantityDto requestedQuantity;
  
  private QuantityDto countedQuantity;
  
  private String id = java.util.UUID.randomUUID().toString();
  
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
   * Returns the requestedQuantity property.
   */
  public QuantityDto getRequestedQuantity() {
    if(this.requestedQuantity== null){
      this.requestedQuantity = new QuantityDto();
    }
    return this.requestedQuantity;
  }
  
  /**
   * Sets the <code>requestedQuantity</code> property to this instance.
   * 
   * @param requestedQuantity - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setRequestedQuantity(final QuantityDto requestedQuantity) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.requestedQuantity != null) {
    	this.requestedQuantity.removePropertyChangeListener(this);
    }
    
    firePropertyChange("requestedQuantity", this.requestedQuantity, this.requestedQuantity = requestedQuantity );
    
    if (this.requestedQuantity != null) {
    	this.requestedQuantity.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the countedQuantity property.
   */
  public QuantityDto getCountedQuantity() {
    if(this.countedQuantity== null){
      this.countedQuantity = new QuantityDto();
    }
    return this.countedQuantity;
  }
  
  /**
   * Sets the <code>countedQuantity</code> property to this instance.
   * 
   * @param countedQuantity - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCountedQuantity(final QuantityDto countedQuantity) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.countedQuantity != null) {
    	this.countedQuantity.removePropertyChangeListener(this);
    }
    
    firePropertyChange("countedQuantity", this.countedQuantity, this.countedQuantity = countedQuantity );
    
    if (this.countedQuantity != null) {
    	this.countedQuantity.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the id property or <code>null</code> if not present.
   */
  public String getId() {
    return this.id;
  }
  
  /**
   * Sets the <code>id</code> property to this instance.
   * 
   * @param id - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setId(final String id) {
    firePropertyChange("id", this.id, this.id = id );
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    InventoryDto other = (InventoryDto) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    return true;
  }
  
  @Override
  public int hashCode() {
     int prime = 31;
    int result = 1;
    result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
    return result;
  }
  
  public InventoryDto createDto() {
    return new InventoryDto();
  }
  
  public InventoryDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    InventoryDto newDto = context.get(this);
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
  
  public void copyContainments(final InventoryDto dto, final InventoryDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy attributes and beans (beans if derived from entity model)
    // copy dto requestedQuantity
    if(getRequestedQuantity() != null) {
    	newDto.setRequestedQuantity(getRequestedQuantity().copy(context));
    }
    // copy dto countedQuantity
    if(getCountedQuantity() != null) {
    	newDto.setCountedQuantity(getCountedQuantity().copy(context));
    }
    // copy id
    newDto.setId(getId());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final InventoryDto dto, final InventoryDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy cross references (cascading is false)
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    if(source == requestedQuantity){
    	firePropertyChange("requestedQuantity" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    if(source == countedQuantity){
    	firePropertyChange("countedQuantity" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	// no super class available to forward event
    }
  }
}
