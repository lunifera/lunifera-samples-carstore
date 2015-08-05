package org.lunifera.samples.carstore.dtos.sales;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;

@SuppressWarnings("all")
public class CarConfigDetailDto implements IDto, Serializable, PropertyChangeListener {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  @Dispose
  private boolean disposed;
  
  private int number;
  
  @DomainReference
  private SalesOrderDetailDto parent;
  
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
   * Returns the parent property or <code>null</code> if not present.
   */
  public SalesOrderDetailDto getParent() {
    return this.parent;
  }
  
  /**
   * Sets the <code>parent</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>SalesOrderDetailDto#
   * configDetails</code> of the <code>parent</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link SalesOrderDetailDto#setConfigDetails(SalesOrderDetailDto)
   * 
   * @param parent - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setParent(final SalesOrderDetailDto parent) {
    checkDisposed();
    if (this.parent != null) {
    	this.parent.internalRemoveFromConfigDetails(this);
    }
    
    internalSetParent(parent);
    
    if (this.parent != null) {
    	this.parent.internalAddToConfigDetails(this);
    }
  }
  
  /**
   * For internal use only!
   */
  public void internalSetParent(final SalesOrderDetailDto parent) {
    firePropertyChange("parent", this.parent, this.parent = parent);
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
    CarConfigDetailDto other = (CarConfigDetailDto) obj;
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
  
  public CarConfigDetailDto createDto() {
    return new CarConfigDetailDto();
  }
  
  public CarConfigDetailDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    CarConfigDetailDto newDto = context.get(this);
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
  
  public void copyContainments(final CarConfigDetailDto dto, final CarConfigDetailDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy attributes and beans (beans if derived from entity model)
    // copy number
    newDto.setNumber(getNumber());
    // copy id
    newDto.setId(getId());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final CarConfigDetailDto dto, final CarConfigDetailDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
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
    { 
    	// no super class available to forward event
    }
  }
}
