package org.lunifera.samples.carstore.entities.sales;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Base;
import org.lunifera.samples.carstore.entities.general.Item;
import org.lunifera.samples.carstore.entities.general.Quantity;

@Entity
@Table(name = "INVENTORY")
@SuppressWarnings("all")
public class Inventory extends Base implements IEntity {
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEM_ID")
  private Item item;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "REQUESTEDQUANTITY_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "uom", joinColumns = @JoinColumn(name = "REQUESTEDQUANTITY_UOM")))
  @Column(name = "REQUESTED_QUANTITY")
  private Quantity requestedQuantity;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "COUNTEDQUANTITY_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "uom", joinColumns = @JoinColumn(name = "COUNTEDQUANTITY_UOM")))
  @Column(name = "COUNTED_QUANTITY")
  private Quantity countedQuantity;
  
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
   * Returns the requestedQuantity property or <code>null</code> if not present.
   */
  public Quantity getRequestedQuantity() {
    checkDisposed();
    return this.requestedQuantity;
  }
  
  /**
   * Sets the requestedQuantity property to this instance.
   */
  public void setRequestedQuantity(final Quantity requestedQuantity) {
    checkDisposed();
    this.requestedQuantity = requestedQuantity;
  }
  
  /**
   * Returns the countedQuantity property or <code>null</code> if not present.
   */
  public Quantity getCountedQuantity() {
    checkDisposed();
    return this.countedQuantity;
  }
  
  /**
   * Sets the countedQuantity property to this instance.
   */
  public void setCountedQuantity(final Quantity countedQuantity) {
    checkDisposed();
    this.countedQuantity = countedQuantity;
  }
}
