package org.lunifera.samples.carstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.Currency;
import org.lunifera.samples.carstore.Customer;
import org.lunifera.samples.carstore.SODetail;

@Entity
@Table(name = "SALES_ORDER")
@SuppressWarnings("all")
public class SalesOrder extends BaseUUID {
  @Column(name = "NUMBER")
  private String number;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CURRENCY")
  private Currency currency;
  
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "CUSTOMER", nullable = false)
  private Customer customer;
  
  @JoinColumn(name = "SO_DETAILS", nullable = false)
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SODetail> soDetails;
  
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
    try {
      // Dispose all the composition references.
      if (this.soDetails != null) {
        for (SODetail sODetail : this.soDetails) {
          sODetail.dispose();
        }
        this.soDetails = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
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
   * Returns the currency property or <code>null</code> if not present.
   */
  public Currency getCurrency() {
    checkDisposed();
    return this.currency;
  }
  
  /**
   * Sets the currency property to this instance.
   */
  public void setCurrency(final Currency currency) {
    checkDisposed();
    this.currency = currency;
  }
  
  /**
   * Returns the <em>required</em> customer property.
   */
  public Customer getCustomer() {
    checkDisposed();
    return this.customer;
  }
  
  /**
   * Sets the customer property to this instance.
   * Since the reference is a container reference, the opposite reference (Customer.orders)
   * of the customer will be handled automatically and no further coding is required to keep them in sync.
   * See {@link Customer#setOrders(Customer)}.
   */
  public void setCustomer(final Customer customer) {
    checkDisposed();
    if (this.customer != null) {
      this.customer.internalRemoveFromOrders(this);
    }
    this.customer = customer;
    if (this.customer != null) {
      this.customer.internalAddToOrders(this);
    }
    
  }
  
  /**
   * Returns an unmodifiable list of soDetails.
   */
  public List<SODetail> getSoDetails() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetSoDetails());
  }
  
  /**
   * Sets the given soDetails to the object. Currently contained soDetails instances will be removed.
   * 
   * @param soDetails the list of new instances
   */
  public void setSoDetails(final List<SODetail> soDetails) {
    // remove the old sODetail
    for(SODetail oldElement : new ArrayList<SODetail>(this.internalGetSoDetails())){
      removeFromSoDetails(oldElement);
    }
    
    // add the new sODetail
    for(SODetail newElement : soDetails){
      addToSoDetails(newElement);
    }
  }
  
  /**
   * Returns the list of <code>SODetail</code>s thereby lazy initializing it.
   */
  private List<SODetail> internalGetSoDetails() {
    if (this.soDetails == null) {
      this.soDetails = new ArrayList<SODetail>();
    }
    return this.soDetails;
  }
  
  /**
   * Adds the given sODetail to this object. <p>
   * Since the reference is a composition reference, the opposite reference (SODetail.order)
   * of the sODetail will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link SODetail#setOrder(SODetail)}.
   * 
   */
  public void addToSoDetails(final SODetail sODetail) {
    checkDisposed();
    sODetail.setOrder(this);
  }
  
  /**
   * Removes the given sODetail from this object. <p>
   * Since the reference is a cascading reference, the opposite reference (SODetail.order)
   * of the sODetail will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link SODetail#setOrder(SODetail)}.
   * 
   */
  public void removeFromSoDetails(final SODetail sODetail) {
    checkDisposed();
    sODetail.setOrder(null);
  }
  
  void internalAddToSoDetails(final SODetail sODetail) {
    internalGetSoDetails().add(sODetail);
  }
  
  void internalRemoveFromSoDetails(final SODetail sODetail) {
    internalGetSoDetails().remove(sODetail);
  }
}
