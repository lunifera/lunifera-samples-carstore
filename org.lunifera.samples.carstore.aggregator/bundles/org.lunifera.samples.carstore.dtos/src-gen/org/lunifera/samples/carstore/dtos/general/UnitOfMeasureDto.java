package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.common.datatypes.IDto;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.dtos.general.NumberedWithDescriptionDto;
import org.lunifera.samples.carstore.dtos.general.SiType;
import org.lunifera.samples.carstore.dtos.general.UOMFamily;

@SuppressWarnings("all")
public class UnitOfMeasureDto extends NumberedWithDescriptionDto implements IDto, Serializable, PropertyChangeListener {
  private SiType siType;
  
  private UOMFamily family;
  
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
   * Returns the siType property or <code>null</code> if not present.
   */
  public SiType getSiType() {
    return this.siType;
  }
  
  /**
   * Sets the <code>siType</code> property to this instance.
   * 
   * @param siType - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setSiType(final SiType siType) {
    firePropertyChange("siType", this.siType, this.siType = siType );
  }
  
  /**
   * Returns the family property or <code>null</code> if not present.
   */
  public UOMFamily getFamily() {
    return this.family;
  }
  
  /**
   * Sets the <code>family</code> property to this instance.
   * 
   * @param family - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setFamily(final UOMFamily family) {
    firePropertyChange("family", this.family, this.family = family );
  }
  
  public UnitOfMeasureDto createDto() {
    return new UnitOfMeasureDto();
  }
  
  public UnitOfMeasureDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    UnitOfMeasureDto newDto = context.get(this);
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
  
  public void copyContainments(final UnitOfMeasureDto dto, final UnitOfMeasureDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy siType
    newDto.setSiType(getSiType());
    // copy family
    newDto.setFamily(getFamily());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final UnitOfMeasureDto dto, final UnitOfMeasureDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
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
