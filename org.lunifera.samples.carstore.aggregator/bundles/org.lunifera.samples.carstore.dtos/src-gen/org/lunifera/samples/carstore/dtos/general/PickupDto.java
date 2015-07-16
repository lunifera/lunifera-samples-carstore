package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.dtos.general.LengthDto;

@SuppressWarnings("all")
public class PickupDto extends CarDto implements Serializable, PropertyChangeListener {
  private LengthDto loadingAreaWidth;
  
  private LengthDto loadingAreaLength;
  
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
   * Returns the loadingAreaWidth property.
   */
  public LengthDto getLoadingAreaWidth() {
    if(this.loadingAreaWidth== null){
      this.loadingAreaWidth = new LengthDto();
    }
    return this.loadingAreaWidth;
  }
  
  /**
   * Sets the <code>loadingAreaWidth</code> property to this instance.
   * 
   * @param loadingAreaWidth - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setLoadingAreaWidth(final LengthDto loadingAreaWidth) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.loadingAreaWidth != null) {
    	this.loadingAreaWidth.removePropertyChangeListener(this);
    }
    
    firePropertyChange("loadingAreaWidth", this.loadingAreaWidth, this.loadingAreaWidth = loadingAreaWidth );
    
    if (this.loadingAreaWidth != null) {
    	this.loadingAreaWidth.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the loadingAreaLength property.
   */
  public LengthDto getLoadingAreaLength() {
    if(this.loadingAreaLength== null){
      this.loadingAreaLength = new LengthDto();
    }
    return this.loadingAreaLength;
  }
  
  /**
   * Sets the <code>loadingAreaLength</code> property to this instance.
   * 
   * @param loadingAreaLength - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setLoadingAreaLength(final LengthDto loadingAreaLength) {
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.loadingAreaLength != null) {
    	this.loadingAreaLength.removePropertyChangeListener(this);
    }
    
    firePropertyChange("loadingAreaLength", this.loadingAreaLength, this.loadingAreaLength = loadingAreaLength );
    
    if (this.loadingAreaLength != null) {
    	this.loadingAreaLength.addPropertyChangeListener(this);
    }
  }
  
  public PickupDto createDto() {
    return new PickupDto();
  }
  
  public PickupDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    PickupDto newDto = context.get(this);
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
  
  public void copyContainments(final PickupDto dto, final PickupDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy dto loadingAreaWidth
    if(getLoadingAreaWidth() != null) {
    	newDto.setLoadingAreaWidth(getLoadingAreaWidth().copy(context));
    }
    // copy dto loadingAreaLength
    if(getLoadingAreaLength() != null) {
    	newDto.setLoadingAreaLength(getLoadingAreaLength().copy(context));
    }
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final PickupDto dto, final PickupDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
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
    if(source == loadingAreaWidth){
    	firePropertyChange("loadingAreaWidth" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    if(source == loadingAreaLength){
    	firePropertyChange("loadingAreaLength" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	super.propertyChange(event);
    }
  }
}
