package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dirty;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.dtos.general.BaseDto;

@SuppressWarnings("all")
public abstract class NumberedWithDescriptionDto extends BaseDto implements IDto, Serializable, PropertyChangeListener {
  @Dirty
  private boolean dirty;
  
  @Size(min = 5, max = 12)
  @NotNull
  @Pattern(regexp = "A[0-9]*")
  private String number;
  
  @Size(min = 3, max = 125)
  @NotNull
  private String description;
  
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
   * Returns the dirty property or <code>null</code> if not present.
   */
  public boolean getDirty() {
    return this.dirty;
  }
  
  /**
   * Sets the <code>dirty</code> property to this instance.
   * 
   * @param dirty - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setDirty(final boolean dirty) {
    firePropertyChange("dirty", this.dirty, this.dirty = dirty );
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
   * Returns the description property or <code>null</code> if not present.
   */
  public String getDescription() {
    return this.description;
  }
  
  /**
   * Sets the <code>description</code> property to this instance.
   * 
   * @param description - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setDescription(final String description) {
    firePropertyChange("description", this.description, this.description = description );
  }
  
  public abstract NumberedWithDescriptionDto copy(final MappingContext context);
  
  public void copyContainments(final NumberedWithDescriptionDto dto, final NumberedWithDescriptionDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy dirty
    newDto.setDirty(getDirty());
    // copy number
    newDto.setNumber(getNumber());
    // copy description
    newDto.setDescription(getDescription());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final NumberedWithDescriptionDto dto, final NumberedWithDescriptionDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
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
    { 
    	super.propertyChange(event);
    }
  }
}
