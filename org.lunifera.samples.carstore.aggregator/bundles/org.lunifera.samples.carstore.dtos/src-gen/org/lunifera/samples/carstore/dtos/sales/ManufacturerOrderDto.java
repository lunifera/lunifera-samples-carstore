package org.lunifera.samples.carstore.dtos.sales;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.BaseDto;
import org.lunifera.samples.carstore.dtos.sales.SalesOrderDetailDto;

@SuppressWarnings("all")
public class ManufacturerOrderDto extends BaseDto implements Serializable, PropertyChangeListener {
  private String number;
  
  @DomainReference
  private SalesOrderDetailDto salesOrderDetail;
  
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
   * Returns the salesOrderDetail property or <code>null</code> if not present.
   */
  public SalesOrderDetailDto getSalesOrderDetail() {
    return this.salesOrderDetail;
  }
  
  /**
   * Sets the <code>salesOrderDetail</code> property to this instance.
   * 
   * @param salesOrderDetail - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setSalesOrderDetail(final SalesOrderDetailDto salesOrderDetail) {
    checkDisposed();
    firePropertyChange("salesOrderDetail", this.salesOrderDetail, this.salesOrderDetail = salesOrderDetail);
  }
  
  public ManufacturerOrderDto createDto() {
    return new ManufacturerOrderDto();
  }
  
  public ManufacturerOrderDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    ManufacturerOrderDto newDto = context.get(this);
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
  
  public void copyContainments(final ManufacturerOrderDto dto, final ManufacturerOrderDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy number
    newDto.setNumber(getNumber());
    
    // copy containment references (cascading is true)
  }
  
  public void copyCrossReferences(final ManufacturerOrderDto dto, final ManufacturerOrderDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyCrossReferences(dto, newDto, context);
    
    // copy cross references (cascading is false)
    // copy dto salesOrderDetail
    if(getSalesOrderDetail() != null) {
    	newDto.setSalesOrderDetail(getSalesOrderDetail().copy(context));
    }
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
