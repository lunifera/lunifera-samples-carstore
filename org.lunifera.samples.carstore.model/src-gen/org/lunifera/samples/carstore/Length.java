package org.lunifera.samples.carstore;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.lunifera.samples.carstore.SITypeEnum;

@Embeddable
@SuppressWarnings("all")
public class Length implements Serializable {
  private boolean disposed;
  
  @Basic
  private int amount;
  
  @Basic
  private SITypeEnum siType;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  @Transient
  public boolean isDisposed() {
    return this.disposed;
  }
  
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
    disposed = true;
  }
  
  /**
   * Returns the amount property or <code>null</code> if not present.
   */
  public int getAmount() {
    checkDisposed();
    return this.amount;
  }
  
  /**
   * Sets the amount property to this instance.
   */
  public void setAmount(final int amount) {
    checkDisposed();
    this.amount = amount;
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
}
