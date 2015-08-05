package org.lunifera.samples.carstore;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@SuppressWarnings("all")
public class Price implements Serializable {
  private boolean disposed;
  
  @Basic
  private int amount;
  
  @Basic
  private String currency;
  
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
   * Returns the currency property or <code>null</code> if not present.
   */
  public String getCurrency() {
    checkDisposed();
    return this.currency;
  }
  
  /**
   * Sets the currency property to this instance.
   */
  public void setCurrency(final String currency) {
    checkDisposed();
    this.currency = currency;
  }
}
