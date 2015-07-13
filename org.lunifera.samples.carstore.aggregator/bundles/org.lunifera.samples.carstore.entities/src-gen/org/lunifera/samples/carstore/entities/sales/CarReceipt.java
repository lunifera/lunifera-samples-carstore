package org.lunifera.samples.carstore.entities.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Base;
import org.lunifera.samples.carstore.entities.sales.ManufacturerOrder;

@Entity
@Table(name = "CAR_RECEIPT")
@SuppressWarnings("all")
public class CarReceipt extends Base implements IEntity {
  @Column(name = "NUMBER")
  private String number;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ORDER_ID")
  private ManufacturerOrder order;
  
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
   * Returns the order property or <code>null</code> if not present.
   */
  public ManufacturerOrder getOrder() {
    checkDisposed();
    return this.order;
  }
  
  /**
   * Sets the order property to this instance.
   */
  public void setOrder(final ManufacturerOrder order) {
    checkDisposed();
    this.order = order;
  }
}
