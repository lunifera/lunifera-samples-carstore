package org.lunifera.samples.carstore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.Item;
import org.lunifera.samples.carstore.SalesOrder;

@Entity
@Table(name = "SO_DETAIL")
@SuppressWarnings("all")
public class SODetail extends BaseUUID {
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ORDER")
  private SalesOrder order;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEM")
  private Item item;
  
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
      if (this.order != null) {
        this.order.dispose();
        this.order = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
  }
  
  /**
   * Returns the order property or <code>null</code> if not present.
   */
  public SalesOrder getOrder() {
    checkDisposed();
    return this.order;
  }
  
  /**
   * Sets the order property to this instance.
   * Since the reference is a container reference, the opposite reference (SalesOrder.soDetails)
   * of the order will be handled automatically and no further coding is required to keep them in sync.
   * See {@link SalesOrder#setSoDetails(SalesOrder)}.
   */
  public void setOrder(final SalesOrder order) {
    checkDisposed();
    if (this.order != null) {
      this.order.internalRemoveFromSoDetails(this);
    }
    this.order = order;
    if (this.order != null) {
      this.order.internalAddToSoDetails(this);
    }
    
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
}
