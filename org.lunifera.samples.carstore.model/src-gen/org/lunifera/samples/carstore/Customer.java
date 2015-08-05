package org.lunifera.samples.carstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lunifera.samples.carstore.Address;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.PaymentTerm;
import org.lunifera.samples.carstore.SalesOrder;

@Entity
@Table(name = "CUSTOMER")
@SuppressWarnings("all")
public class Customer extends BaseUUID {
  @Column(name = "NUMBER")
  private String number;
  
  @Column(name = "NAME")
  private String name;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")), @AttributeOverride(name = "postalcode", column = @Column(name = "ADDRESS_POSTALCODE")) })
  @Column(name = "ADDRESS")
  private Address address;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PAYMENT_TERM")
  private PaymentTerm paymentTerm;
  
  @JoinColumn(name = "ORDERS")
  @OneToMany(mappedBy = "customer")
  private List<SalesOrder> orders;
  
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
   * Returns the address property or <code>null</code> if not present.
   */
  public Address getAddress() {
    checkDisposed();
    return this.address;
  }
  
  /**
   * Sets the address property to this instance.
   */
  public void setAddress(final Address address) {
    checkDisposed();
    this.address = address;
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
  
  /**
   * Returns an unmodifiable list of orders.
   */
  public List<SalesOrder> getOrders() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetOrders());
  }
  
  /**
   * Sets the given orders to the object. Currently contained orders instances will be removed.
   * 
   * @param orders the list of new instances
   */
  public void setOrders(final List<SalesOrder> orders) {
    // remove the old salesOrder
    for(SalesOrder oldElement : new ArrayList<SalesOrder>(this.internalGetOrders())){
      removeFromOrders(oldElement);
    }
    
    // add the new salesOrder
    for(SalesOrder newElement : orders){
      addToOrders(newElement);
    }
  }
  
  /**
   * Returns the list of <code>SalesOrder</code>s thereby lazy initializing it.
   */
  private List<SalesOrder> internalGetOrders() {
    if (this.orders == null) {
      this.orders = new ArrayList<SalesOrder>();
    }
    return this.orders;
  }
  
  /**
   * Adds the given salesOrder to this object. <p>
   * Since the reference is a composition reference, the opposite reference (SalesOrder.customer)
   * of the salesOrder will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link SalesOrder#setCustomer(SalesOrder)}.
   * 
   */
  public void addToOrders(final SalesOrder salesOrder) {
    checkDisposed();
    salesOrder.setCustomer(this);
  }
  
  /**
   * Removes the given salesOrder from this object. <p>
   * 
   */
  public void removeFromOrders(final SalesOrder salesOrder) {
    checkDisposed();
    salesOrder.setCustomer(null);
  }
  
  void internalAddToOrders(final SalesOrder salesOrder) {
    internalGetOrders().add(salesOrder);
  }
  
  void internalRemoveFromOrders(final SalesOrder salesOrder) {
    internalGetOrders().remove(salesOrder);
  }
}
