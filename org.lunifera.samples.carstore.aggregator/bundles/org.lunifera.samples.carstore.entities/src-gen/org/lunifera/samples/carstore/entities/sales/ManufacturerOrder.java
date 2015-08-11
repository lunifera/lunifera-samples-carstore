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
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

@Entity
@Table(name = "MANUFACTURER_ORDER")
@SuppressWarnings("all")
public class ManufacturerOrder extends Base implements IEntity {
  @Column(name = "NUMBER")
  private String number;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "SALES_ORDER_DETAIL_ID")
  private SalesOrderDetail salesOrderDetail;
  
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
   * Returns the salesOrderDetail property or <code>null</code> if not present.
   */
  public SalesOrderDetail getSalesOrderDetail() {
    checkDisposed();
    return this.salesOrderDetail;
  }
  
  /**
   * Sets the salesOrderDetail property to this instance.
   */
  public void setSalesOrderDetail(final SalesOrderDetail salesOrderDetail) {
    checkDisposed();
    this.salesOrderDetail = salesOrderDetail;
  }
}
