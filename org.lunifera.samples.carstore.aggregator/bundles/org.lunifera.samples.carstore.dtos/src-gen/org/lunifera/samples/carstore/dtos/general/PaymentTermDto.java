package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.dtos.general.NumberedWithDescriptionDto;

@SuppressWarnings("all")
public class PaymentTermDto extends NumberedWithDescriptionDto implements Serializable, PropertyChangeListener {
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
  
  public PaymentTermDto createDto() {
    return new PaymentTermDto();
  }
  
  public PaymentTermDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    PaymentTermDto newDto = context.get(this);
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
  
  public void copyContainments(final PaymentTermDto dto, final PaymentTermDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final PaymentTermDto dto, final PaymentTermDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
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
