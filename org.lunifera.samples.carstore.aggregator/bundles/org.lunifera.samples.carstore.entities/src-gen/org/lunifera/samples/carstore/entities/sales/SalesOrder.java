package org.lunifera.samples.carstore.entities.sales;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Base;
import org.lunifera.samples.carstore.entities.general.Customer;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;

@Entity
@Table(name = "SALES_ORDER")
@SuppressWarnings("all")
public class SalesOrder extends Base {
  @Column(name = "NUMBER")
  private String number;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER_ID")
  private Customer customer;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PAYMENT_TERM_ID")
  private PaymentTerm paymentTerm;
  
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
   * Returns the customer property or <code>null</code> if not present.
   */
  public Customer getCustomer() {
    checkDisposed();
    return this.customer;
  }
  
  /**
   * Sets the customer property to this instance.
   */
  public void setCustomer(final Customer customer) {
    checkDisposed();
    this.customer = customer;
  }
  
  /**
   * Returns the paymentTerm property or <code>null</code> if not present.
   */
  public PaymentTerm getPaymentTerm() {
    checkDisposed();
    return this.paymentTerm;
  }
  
  /**
   * Sets the paymentTerm property to this instance.
   */
  public void setPaymentTerm(final PaymentTerm paymentTerm) {
    checkDisposed();
    this.paymentTerm = paymentTerm;
  }
}
