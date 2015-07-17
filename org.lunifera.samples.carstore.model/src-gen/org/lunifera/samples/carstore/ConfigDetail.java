package org.lunifera.samples.carstore;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.samples.carstore.BaseUUID;
import org.lunifera.samples.carstore.Car;
import org.lunifera.samples.carstore.Price;

@Entity
@Table(name = "CONFIG_DETAIL")
@SuppressWarnings("all")
public class ConfigDetail extends BaseUUID {
  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "CAR", nullable = false)
  private Car car;
  
  @Column(name = "NUMBER")
  private String number;
  
  @Column(name = "DESCRIPTION")
  private String description;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "amount", column = @Column(name = "PRICE_AMOUNT")), @AttributeOverride(name = "currency", column = @Column(name = "PRICE_CURRENCY")) })
  @Column(name = "PRICE")
  private Price price;
  
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
   * Returns the <em>required</em> car property.
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
   * Returns the description property or <code>null</code> if not present.
   */
  public String getDescription() {
    checkDisposed();
    return this.description;
  }
  
  /**
   * Sets the description property to this instance.
   */
  public void setDescription(final String description) {
    checkDisposed();
    this.description = description;
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
}
