package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import org.lunifera.samples.carstore.SITypeEnum;
import org.lunifera.samples.carstore.UOMFamilyEnum;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;

@SuppressWarnings("all")
public class UnitOfMeasureDto extends BaseUUIDDto implements Serializable {
  private String number;
  
  private String name;
  
  private SITypeEnum siType;
  
  private UOMFamilyEnum family;
  
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
   * Returns the name property or <code>null</code> if not present.
   */
  public String getName() {
    checkDisposed();
    return this.name;
  }
  
  /**
   * Sets the <code>name</code> property to this instance.
   * 
   * @param name - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setName(final String name) {
    firePropertyChange("name", this.name, this.name = name );
  }
  
  /**
   * Returns the siType property or <code>null</code> if not present.
   */
  public SITypeEnum getSiType() {
    checkDisposed();
    return this.siType;
  }
  
  /**
   * Sets the <code>siType</code> property to this instance.
   * 
   * @param siType - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setSiType(final SITypeEnum siType) {
    firePropertyChange("siType", this.siType, this.siType = siType );
  }
  
  /**
   * Returns the family property or <code>null</code> if not present.
   */
  public UOMFamilyEnum getFamily() {
    checkDisposed();
    return this.family;
  }
  
  /**
   * Sets the <code>family</code> property to this instance.
   * 
   * @param family - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setFamily(final UOMFamilyEnum family) {
    firePropertyChange("family", this.family, this.family = family );
  }
}
