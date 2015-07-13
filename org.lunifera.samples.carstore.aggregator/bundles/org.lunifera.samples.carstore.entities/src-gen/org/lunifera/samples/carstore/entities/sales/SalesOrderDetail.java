package org.lunifera.samples.carstore.entities.sales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Amount;
import org.lunifera.samples.carstore.entities.general.Base;
import org.lunifera.samples.carstore.entities.general.Item;
import org.lunifera.samples.carstore.entities.general.PaymentTerm;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.Quantity;
import org.lunifera.samples.carstore.entities.sales.CarConfigDetail;

@Entity
@Table(name = "SALES_ORDER_DETAIL")
@SuppressWarnings("all")
public class SalesOrderDetail extends Base implements IEntity {
  @Column(name = "NUMBER")
  private int number;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEM_ID")
  private Item item;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PAYMENT_TERM_ID")
  private PaymentTerm paymentTerm;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "PRICE_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "currency", joinColumns = @JoinColumn(name = "PRICE_CURRENCY")))
  @Column(name = "PRICE")
  private Price price;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "QUANTITY_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "uom", joinColumns = @JoinColumn(name = "QUANTITY_UOM")))
  @Column(name = "QUANTITY")
  private Quantity quantity;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "VALUE_AMOUNT")))
  @Column(name = "VALUE")
  private Amount value;
  
  @JoinColumn(name = "CONFIG_DETAILS")
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CarConfigDetail> configDetails;
  
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
    try {
      // Dispose all the composition references.
      if (this.configDetails != null) {
        for (CarConfigDetail carConfigDetail : this.configDetails) {
          carConfigDetail.dispose();
        }
        this.configDetails = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
  }
  
  /**
   * Returns the number property or <code>null</code> if not present.
   */
  public int getNumber() {
    checkDisposed();
    return this.number;
  }
  
  /**
   * Sets the number property to this instance.
   */
  public void setNumber(final int number) {
    checkDisposed();
    this.number = number;
  }
  
  /**
   * Returns the item property or <code>null</code> if not present.
   */
  public Item getItem() {
    checkDisposed();
    return this.item;
  }
  
  /**
   * Sets the item property to this instance.
   */
  public void setItem(final Item item) {
    checkDisposed();
    this.item = item;
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
   * Returns the price property or <code>null</code> if not present.
   */
  public Price getPrice() {
    checkDisposed();
    return this.price;
  }
  
  /**
   * Sets the price property to this instance.
   */
  public void setPrice(final Price price) {
    checkDisposed();
    this.price = price;
  }
  
  /**
   * Returns the quantity property or <code>null</code> if not present.
   */
  public Quantity getQuantity() {
    checkDisposed();
    return this.quantity;
  }
  
  /**
   * Sets the quantity property to this instance.
   */
  public void setQuantity(final Quantity quantity) {
    checkDisposed();
    this.quantity = quantity;
  }
  
  /**
   * Returns the value property or <code>null</code> if not present.
   */
  public Amount getValue() {
    checkDisposed();
    return this.value;
  }
  
  /**
   * Sets the value property to this instance.
   */
  public void setValue(final Amount value) {
    checkDisposed();
    this.value = value;
  }
  
  /**
   * Returns an unmodifiable list of configDetails.
   */
  public List<CarConfigDetail> getConfigDetails() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetConfigDetails());
  }
  
  /**
   * Sets the given configDetails to the object. Currently contained configDetails instances will be removed.
   * 
   * @param configDetails the list of new instances
   */
  public void setConfigDetails(final List<CarConfigDetail> configDetails) {
    // remove the old carConfigDetail
    for(CarConfigDetail oldElement : new ArrayList<CarConfigDetail>(this.internalGetConfigDetails())){
      removeFromConfigDetails(oldElement);
    }
    
    // add the new carConfigDetail
    for(CarConfigDetail newElement : configDetails){
      addToConfigDetails(newElement);
    }
  }
  
  /**
   * Returns the list of <code>CarConfigDetail</code>s thereby lazy initializing it.
   */
  private List<CarConfigDetail> internalGetConfigDetails() {
    if (this.configDetails == null) {
      this.configDetails = new ArrayList<CarConfigDetail>();
    }
    return this.configDetails;
  }
  
  /**
   * Adds the given carConfigDetail to this object. <p>
   * Since the reference is a composition reference, the opposite reference (CarConfigDetail.parent)
   * of the carConfigDetail will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link CarConfigDetail#setParent(CarConfigDetail)}.
   * 
   */
  public void addToConfigDetails(final CarConfigDetail carConfigDetail) {
    checkDisposed();
    carConfigDetail.setParent(this);
  }
  
  /**
   * Removes the given carConfigDetail from this object. <p>
   * Since the reference is a cascading reference, the opposite reference (CarConfigDetail.parent)
   * of the carConfigDetail will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link CarConfigDetail#setParent(CarConfigDetail)}.
   * 
   */
  public void removeFromConfigDetails(final CarConfigDetail carConfigDetail) {
    checkDisposed();
    carConfigDetail.setParent(null);
  }
  
  /**
   * For internal use only!
   */
  void internalAddToConfigDetails(final CarConfigDetail carConfigDetail) {
    internalGetConfigDetails().add(carConfigDetail);
  }
  
  /**
   * For internal use only!
   */
  void internalRemoveFromConfigDetails(final CarConfigDetail carConfigDetail) {
    internalGetConfigDetails().remove(carConfigDetail);
  }
}
