package org.lunifera.samples.carstore;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lunifera.samples.carstore.Car;
import org.lunifera.samples.carstore.RoofType;

@Entity
@Table(name = "CONVERTIBLE")
@DiscriminatorValue(value = "CONVERTIBLE")
@SuppressWarnings("all")
public class Convertible extends Car {
  @Column(name = "ROOF_TYPE")
  private RoofType roofType;
  
  @Column(name = "COLOR")
  private String color;
  
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
   * Returns the roofType property or <code>null</code> if not present.
   */
  public RoofType getRoofType() {
    checkDisposed();
    return this.roofType;
  }
  
  /**
   * Sets the roofType property to this instance.
   */
  public void setRoofType(final RoofType roofType) {
    checkDisposed();
    this.roofType = roofType;
  }
  
  /**
   * Returns the color property or <code>null</code> if not present.
   */
  public String getColor() {
    checkDisposed();
    return this.color;
  }
  
  /**
   * Sets the color property to this instance.
   */
  public void setColor(final String color) {
    checkDisposed();
    this.color = color;
  }
}
