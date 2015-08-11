package org.lunifera.samples.carstore.entities.general;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Car;
import org.lunifera.samples.carstore.entities.general.NumberedWithDescription;
import org.lunifera.samples.carstore.entities.general.Price;

@Entity
@Table(name = "CONFIG_DETAIL_DEFINITION")
@DiscriminatorValue(value = "CONFIG_DETAIL_DEFINITION")
@SuppressWarnings("all")
public class ConfigDetailDefinition extends NumberedWithDescription implements IEntity {
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "PRICE_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "currency", joinColumns = @JoinColumn(name = "PRICE_CURRENCY")))
  @Column(name = "PRICE")
  @NotNull
  private Price price;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "CAR_ID")
  private Car car;
  
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
      if (this.car != null) {
        this.car.dispose();
        this.car = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
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
   * Returns the car property or <code>null</code> if not present.
   */
  public Car getCar() {
    checkDisposed();
    return this.car;
  }
  
  /**
   * Sets the car property to this instance.
   * Since the reference is a container reference, the opposite reference (Car.configDetails)
   * of the car will be handled automatically and no further coding is required to keep them in sync.
   * See {@link Car#setConfigDetails(Car)}.
   */
  public void setCar(final Car car) {
    checkDisposed();
    if (this.car != null) {
      this.car.internalRemoveFromConfigDetails(this);
    }
    this.car = car;
    if (this.car != null) {
      this.car.internalAddToConfigDetails(this);
    }
    
  }
}
