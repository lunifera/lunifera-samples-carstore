package org.lunifera.samples.carstore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.SITypeEnum;
import org.lunifera.samples.carstore.UOMFamilyEnum;

@Entity
@Table(name = "UNIT_OF_MEASURE")
@SuppressWarnings("all")
public class UnitOfMeasure extends BaseUUID {
  @Column(name = "NUMBER")
  private String number;
  
  @Column(name = "NAME")
  private String name;
  
  @Column(name = "SI_TYPE")
  private SITypeEnum siType;
  
  @Column(name = "FAMILY")
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
   * Sets the number property to this instance.
   */
  public void setNumber(final String number) {
    checkDisposed();
    this.number = number;
  }
  
  /**
   * Returns the name property or <code>null</code> if not present.
   */
  public String getName() {
    checkDisposed();
    return this.name;
  }
  
  /**
   * Sets the name property to this instance.
   */
  public void setName(final String name) {
    checkDisposed();
    this.name = name;
  }
  
  /**
   * Returns the siType property or <code>null</code> if not present.
   */
  public SITypeEnum getSiType() {
    checkDisposed();
    return this.siType;
  }
  
  /**
   * Sets the siType property to this instance.
   */
  public void setSiType(final SITypeEnum siType) {
    checkDisposed();
    this.siType = siType;
  }
  
  /**
   * Returns the family property or <code>null</code> if not present.
   */
  public UOMFamilyEnum getFamily() {
    checkDisposed();
    return this.family;
  }
  
  /**
   * Sets the family property to this instance.
   */
  public void setFamily(final UOMFamilyEnum family) {
    checkDisposed();
    this.family = family;
  }
}
