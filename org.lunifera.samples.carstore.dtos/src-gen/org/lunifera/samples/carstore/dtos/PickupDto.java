package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import org.lunifera.samples.carstore.dtos.CarDto;
import org.lunifera.samples.carstore.dtos.LengthDto;

@SuppressWarnings("all")
public class PickupDto extends CarDto implements Serializable {
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
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    super.dispose();
  }
  
  /**
   * Returns the loadingAreaWidth property or <code>null</code> if not present.
   */
  public LengthDto getLoadingAreaWidth() {
    checkDisposed();
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
    firePropertyChange("loadingAreaWidth", this.loadingAreaWidth, this.loadingAreaWidth = loadingAreaWidth );
  }
  
  /**
   * Returns the loadingAreaLength property or <code>null</code> if not present.
   */
  public LengthDto getLoadingAreaLength() {
    checkDisposed();
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
    firePropertyChange("loadingAreaLength", this.loadingAreaLength, this.loadingAreaLength = loadingAreaLength );
  }
}
