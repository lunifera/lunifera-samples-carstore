package org.lunifera.samples.carstore;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.UnitOfMeasure;

@Entity
@Table(name = "ITEM")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISC", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "ITEM")
@SuppressWarnings("all")
public abstract class Item extends BaseUUID {
  @Column(name = "NUMBER")
  private String number;
  
  @Column(name = "DESCRIPTION")
  private String description;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UOM")
  private UnitOfMeasure uom;
  
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
   * Returns the description property or <code>null</code> if not present.
   */
  public String getDescription() {
    checkDisposed();
    return this.description;
  }
  
  /**
   * Sets the description property to this instance.
   */
  public void setDescription(final String description) {
    checkDisposed();
    this.description = description;
  }
  
  /**
   * Returns the uom property or <code>null</code> if not present.
   */
  public UnitOfMeasure getUom() {
    checkDisposed();
    return this.uom;
  }
  
  /**
   * Sets the uom property to this instance.
   */
  public void setUom(final UnitOfMeasure uom) {
    checkDisposed();
    this.uom = uom;
  }
}
