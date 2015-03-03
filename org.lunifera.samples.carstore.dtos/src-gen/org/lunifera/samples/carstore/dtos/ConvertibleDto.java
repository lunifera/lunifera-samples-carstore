package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import org.lunifera.samples.carstore.RoofType;
import org.lunifera.samples.carstore.dtos.CarDto;

@SuppressWarnings("all")
public class ConvertibleDto extends CarDto implements Serializable {
  private RoofType roofType;
  
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
   * Sets the <code>roofType</code> property to this instance.
   * 
   * @param roofType - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setRoofType(final RoofType roofType) {
    firePropertyChange("roofType", this.roofType, this.roofType = roofType );
  }
  
  /**
   * Returns the color property or <code>null</code> if not present.
   */
  public String getColor() {
    checkDisposed();
    return this.color;
  }
  
  /**
   * Sets the <code>color</code> property to this instance.
   * 
   * @param color - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setColor(final String color) {
    firePropertyChange("color", this.color, this.color = color );
  }
}
