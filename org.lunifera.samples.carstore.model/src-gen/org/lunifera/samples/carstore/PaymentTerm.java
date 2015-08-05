package org.lunifera.samples.carstore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lunifera.samples.carstore.BaseUUID;

@Entity
@Table(name = "PAYMENT_TERM")
@SuppressWarnings("all")
public class PaymentTerm extends BaseUUID {
  @Column(name = "NUMBER")
  private String number;
  
  @Column(name = "DESCRIPTION")
  private String description;
  
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
}
