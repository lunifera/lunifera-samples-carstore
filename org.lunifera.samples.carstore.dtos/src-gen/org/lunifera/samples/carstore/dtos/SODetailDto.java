package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;
import org.lunifera.samples.carstore.dtos.ItemDto;
import org.lunifera.samples.carstore.dtos.SalesOrderDto;

@SuppressWarnings("all")
public class SODetailDto extends BaseUUIDDto implements Serializable {
  @DomainReference
  private SalesOrderDto order;
  
  @DomainReference
  private ItemDto item;
  
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
   * Returns the order property or <code>null</code> if not present.
   */
  public SalesOrderDto getOrder() {
    checkDisposed();
    return this.order;
  }
  
  /**
   * Sets the <code>order</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>SalesOrderDto#
   * soDetails</code> of the <code>order</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link SalesOrderDto#setSoDetails(SalesOrderDto)
   * 
   * @param order - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setOrder(final SalesOrderDto order) {
    checkDisposed();
    if (this.order != null) {
      this.order.internalRemoveFromSoDetails(this);
    }
    internalSetOrder(order);
    if (this.order != null) {
      this.order.internalAddToSoDetails(this);
    }
    
  }
  
  /**
   * For internal use only!
   */
  public void internalSetOrder(final SalesOrderDto order) {
    firePropertyChange("order", this.order, this.order = order);
  }
  
  /**
   * Returns the item property or <code>null</code> if not present.
   */
  public ItemDto getItem() {
    checkDisposed();
    return this.item;
  }
  
  /**
   * Sets the <code>item</code> property to this instance.
   * 
   * @param item - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setItem(final ItemDto item) {
    checkDisposed();
    firePropertyChange("item", this.item, this.item = item);
  }
}
