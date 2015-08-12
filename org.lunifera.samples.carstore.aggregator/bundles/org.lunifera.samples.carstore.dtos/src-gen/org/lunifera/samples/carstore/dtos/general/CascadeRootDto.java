package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.CascadeNoDto;
import org.lunifera.samples.carstore.dtos.general.CascadeYesDto;

@SuppressWarnings("all")
public class CascadeRootDto implements IDto, Serializable, PropertyChangeListener {
  private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
  
  @Dispose
  private boolean disposed;
  
  private String id = java.util.UUID.randomUUID().toString();
  
  @DomainReference
  private List<CascadeYesDto> refYes;
  
  @DomainReference
  private List<CascadeNoDto> refNo;
  
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
    try {
      // Dispose all the composition references.
      if (this.refYes != null) {
        for (CascadeYesDto cascadeYesDto : this.refYes) {
          cascadeYesDto.dispose();
        }
        this.refYes = null;
      }
      
    }
    finally {
      firePropertyChange("disposed", this.disposed, this.disposed = true);
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
  
  /**
   * Returns an unmodifiable list of refYes.
   */
  public List<CascadeYesDto> getRefYes() {
    return Collections.unmodifiableList(internalGetRefYes());
  }
  
  /**
   * Returns the list of <code>CascadeYesDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<CascadeYesDto> internalGetRefYes() {
    if (this.refYes == null) {
      this.refYes = new java.util.ArrayList<CascadeYesDto>();
    }
    return this.refYes;
  }
  
  /**
   * Adds the given cascadeYesDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>CascadeYesDto#root</code> of the <code>cascadeYesDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link CascadeYesDto#setRoot(CascadeYesDto)}.
   * 
   * @param cascadeYesDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToRefYes(final CascadeYesDto cascadeYesDto) {
    checkDisposed();
    
    cascadeYesDto.setRoot(this);
  }
  
  /**
   * Removes the given cascadeYesDto from this object. <p>
   * 
   * @param cascadeYesDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromRefYes(final CascadeYesDto cascadeYesDto) {
    checkDisposed();
    
    cascadeYesDto.setRoot(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToRefYes(final CascadeYesDto cascadeYesDto) {
    internalGetRefYes().add(cascadeYesDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromRefYes(final CascadeYesDto cascadeYesDto) {
    internalGetRefYes().remove(cascadeYesDto);
  }
  
  /**
   * Sets the <code>refYes</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>CascadeYesDto#
   * root</code> of the <code>refYes</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link CascadeYesDto#setRoot(CascadeYesDto)
   * 
   * @param refYes - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setRefYes(final List<CascadeYesDto> refYes) {
    checkDisposed();
    for (CascadeYesDto dto : internalGetRefYes().toArray(new CascadeYesDto[this.refYes.size()])) {
    	removeFromRefYes(dto);
    }
    
    if(refYes == null) {
    	return;
    }
    
    for (CascadeYesDto dto : refYes) {
    	addToRefYes(dto);
    }
  }
  
  /**
   * Returns an unmodifiable list of refNo.
   */
  public List<CascadeNoDto> getRefNo() {
    return Collections.unmodifiableList(internalGetRefNo());
  }
  
  /**
   * Returns the list of <code>CascadeNoDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<CascadeNoDto> internalGetRefNo() {
    if (this.refNo == null) {
      this.refNo = new java.util.ArrayList<CascadeNoDto>();
    }
    return this.refNo;
  }
  
  /**
   * Adds the given cascadeNoDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>CascadeNoDto#root</code> of the <code>cascadeNoDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link CascadeNoDto#setRoot(CascadeNoDto)}.
   * 
   * @param cascadeNoDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToRefNo(final CascadeNoDto cascadeNoDto) {
    checkDisposed();
    
    cascadeNoDto.setRoot(this);
  }
  
  /**
   * Removes the given cascadeNoDto from this object. <p>
   * 
   * @param cascadeNoDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromRefNo(final CascadeNoDto cascadeNoDto) {
    checkDisposed();
    
    cascadeNoDto.setRoot(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToRefNo(final CascadeNoDto cascadeNoDto) {
    internalGetRefNo().add(cascadeNoDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromRefNo(final CascadeNoDto cascadeNoDto) {
    internalGetRefNo().remove(cascadeNoDto);
  }
  
  /**
   * Sets the <code>refNo</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>CascadeNoDto#
   * root</code> of the <code>refNo</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link CascadeNoDto#setRoot(CascadeNoDto)
   * 
   * @param refNo - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setRefNo(final List<CascadeNoDto> refNo) {
    checkDisposed();
    for (CascadeNoDto dto : internalGetRefNo().toArray(new CascadeNoDto[this.refNo.size()])) {
    	removeFromRefNo(dto);
    }
    
    if(refNo == null) {
    	return;
    }
    
    for (CascadeNoDto dto : refNo) {
    	addToRefNo(dto);
    }
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CascadeRootDto other = (CascadeRootDto) obj;
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
  
  public CascadeRootDto createDto() {
    return new CascadeRootDto();
  }
  
  public CascadeRootDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    CascadeRootDto newDto = context.get(this);
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
  
  public void copyContainments(final CascadeRootDto dto, final CascadeRootDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy attributes and beans (beans if derived from entity model)
    // copy id
    newDto.setId(getId());
    
    // copy containment references (cascading is true)
    // copy list of refYes dtos
    for(org.lunifera.samples.carstore.dtos.general.CascadeYesDto _dto : getRefYes()) {
    	newDto.addToRefYes(_dto.copy(context));
    }
  }
  
  public void copyCrossReferences(final CascadeRootDto dto, final CascadeRootDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    
    // copy cross references (cascading is false)
    // copy list of refNo dtos
    for(org.lunifera.samples.carstore.dtos.general.CascadeNoDto _dto : getRefNo()) {
    	newDto.addToRefNo(_dto.copy(context));
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
