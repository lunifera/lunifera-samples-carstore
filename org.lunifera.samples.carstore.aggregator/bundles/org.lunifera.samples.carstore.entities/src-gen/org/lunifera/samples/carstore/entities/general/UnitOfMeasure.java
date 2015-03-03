package org.lunifera.samples.carstore.entities.general;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.NumberedWithDescription;
import org.lunifera.samples.carstore.entities.general.SiType;
import org.lunifera.samples.carstore.entities.general.UOMFamily;

@Entity
@Table(name = "UNIT_OF_MEASURE")
@DiscriminatorValue(value = "UNIT_OF_MEASURE")
@SuppressWarnings("all")
public class UnitOfMeasure extends NumberedWithDescription {
  @Column(name = "SI_TYPE")
  private SiType siType;
  
  @Column(name = "FAMILY")
  private UOMFamily family;
  
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
   * Returns the siType property or <code>null</code> if not present.
   */
  public SiType getSiType() {
    checkDisposed();
    return this.siType;
  }
  
  /**
   * Sets the siType property to this instance.
   */
  public void setSiType(final SiType siType) {
    checkDisposed();
    this.siType = siType;
  }
  
  /**
   * Returns the family property or <code>null</code> if not present.
   */
  public UOMFamily getFamily() {
    checkDisposed();
    return this.family;
  }
  
  /**
   * Sets the family property to this instance.
   */
  public void setFamily(final UOMFamily family) {
    checkDisposed();
    this.family = family;
  }
}
