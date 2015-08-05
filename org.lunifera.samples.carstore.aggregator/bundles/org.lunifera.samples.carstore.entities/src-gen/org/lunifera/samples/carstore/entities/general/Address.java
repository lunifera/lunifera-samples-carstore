package org.lunifera.samples.carstore.entities.general;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import org.lunifera.runtime.common.annotations.Dispose;

@Embeddable
@SuppressWarnings("all")
public class Address implements Serializable {
  @Transient
  @Dispose
  private boolean disposed;
  
  @Basic
  private String street;
  
  @Basic
  private String postalCode;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  @Dispose
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
  @Dispose
  public void dispose() {
    if (isDisposed()) {
      return;
    }
    disposed = true;
  }
  
  /**
   * Returns the street property or <code>null</code> if not present.
   */
  public String getStreet() {
    checkDisposed();
    return this.street;
  }
  
  /**
   * Sets the street property to this instance.
   */
  public void setStreet(final String street) {
    checkDisposed();
    this.street = street;
  }
  
  /**
   * Returns the postalCode property or <code>null</code> if not present.
   */
  public String getPostalCode() {
    checkDisposed();
    return this.postalCode;
  }
  
  /**
   * Sets the postalCode property to this instance.
   */
  public void setPostalCode(final String postalCode) {
    checkDisposed();
    this.postalCode = postalCode;
  }
}
