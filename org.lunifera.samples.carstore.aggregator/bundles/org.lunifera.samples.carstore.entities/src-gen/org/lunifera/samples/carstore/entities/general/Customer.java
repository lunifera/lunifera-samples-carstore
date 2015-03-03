package org.lunifera.samples.carstore.entities.general;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Address;
import org.lunifera.samples.carstore.entities.general.Base;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;

@Entity
@Table(name = "CUSTOMER")
@SuppressWarnings("all")
public class Customer extends Base {
  @Column(name = "NUMBER")
  private String number;
  
  @Column(name = "NAME")
  private String name;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PAYMENT_TERMS_ID")
  private PaymentTerm paymentTerms;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "DELIVERYADDRESS_STREET")), @AttributeOverride(name = "postalCode", column = @Column(name = "DELIVERYADDRESS_POSTALCODE")) })
  @Column(name = "DELIVERY_ADDRESS")
  private Address deliveryAddress;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "INVOICEADDRESS_STREET")), @AttributeOverride(name = "postalCode", column = @Column(name = "INVOICEADDRESS_POSTALCODE")) })
  @Column(name = "INVOICE_ADDRESS")
  private Address invoiceAddress;
  
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
   * Returns the name property or <code>null</code> if not present.
   */
  public String getName() {
    checkDisposed();
    return this.name;
  }
  
  /**
   * Sets the name property to this instance.
   */
  public void setName(final String name) {
    checkDisposed();
    this.name = name;
  }
  
  /**
   * Returns the paymentTerms property or <code>null</code> if not present.
   */
  public PaymentTerm getPaymentTerms() {
    checkDisposed();
    return this.paymentTerms;
  }
  
  /**
   * Sets the paymentTerms property to this instance.
   */
  public void setPaymentTerms(final PaymentTerm paymentTerms) {
    checkDisposed();
    this.paymentTerms = paymentTerms;
  }
  
  /**
   * Returns the deliveryAddress property or <code>null</code> if not present.
   */
  public Address getDeliveryAddress() {
    checkDisposed();
    return this.deliveryAddress;
  }
  
  /**
   * Sets the deliveryAddress property to this instance.
   */
  public void setDeliveryAddress(final Address deliveryAddress) {
    checkDisposed();
    this.deliveryAddress = deliveryAddress;
  }
  
  /**
   * Returns the invoiceAddress property or <code>null</code> if not present.
   */
  public Address getInvoiceAddress() {
    checkDisposed();
    return this.invoiceAddress;
  }
  
  /**
   * Sets the invoiceAddress property to this instance.
   */
  public void setInvoiceAddress(final Address invoiceAddress) {
    checkDisposed();
    this.invoiceAddress = invoiceAddress;
  }
}
