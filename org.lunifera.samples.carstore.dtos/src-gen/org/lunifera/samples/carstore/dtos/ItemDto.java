package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;
import org.lunifera.samples.carstore.dtos.UnitOfMeasureDto;

@SuppressWarnings("all")
public class ItemDto extends BaseUUIDDto implements Serializable {
  private String number;
  
  private String description;
  
  @DomainReference
  private UnitOfMeasureDto uom;
  
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
    checkDisposed();
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
    checkDisposed();
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
  
  /**
   * Returns the uom property or <code>null</code> if not present.
   */
  public UnitOfMeasureDto getUom() {
    checkDisposed();
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
}
