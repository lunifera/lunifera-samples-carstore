package org.lunifera.samples.carstore.entities.general;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.NumberedWithDescription;

@Entity
@Table(name = "CURRENCY")
@DiscriminatorValue(value = "CURRENCY")
@SuppressWarnings("all")
public class Currency extends NumberedWithDescription {
  @Column(name = "ISO3_CODE")
  private String iso3Code;
  
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
    super.dispose();
  }
  
  /**
   * Returns the iso3Code property or <code>null</code> if not present.
   */
  public String getIso3Code() {
    checkDisposed();
    return this.iso3Code;
  }
  
  /**
   * Sets the iso3Code property to this instance.
   */
  public void setIso3Code(final String iso3Code) {
    checkDisposed();
    this.iso3Code = iso3Code;
  }
}
