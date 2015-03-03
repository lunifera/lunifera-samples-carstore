package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.BaseUUIDDto;
import org.lunifera.samples.carstore.dtos.CarDto;
import org.lunifera.samples.carstore.dtos.PriceDto;

@SuppressWarnings("all")
public class ConfigDetailDto extends BaseUUIDDto implements Serializable {
  @DomainReference
  private CarDto car;
  
  private String number;
  
  private String description;
  
  private PriceDto price;
  
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
  public CarDto getCar() {
    checkDisposed();
    return this.car;
  }
  
  /**
   * Sets the <code>car</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>CarDto#
   * configDetails</code> of the <code>car</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link CarDto#setConfigDetails(CarDto)
   * 
   * @param car - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setCar(final CarDto car) {
    checkDisposed();
    if (this.car != null) {
      this.car.internalRemoveFromConfigDetails(this);
    }
    internalSetCar(car);
    if (this.car != null) {
      this.car.internalAddToConfigDetails(this);
    }
    
  }
  
  /**
   * For internal use only!
   */
  public void internalSetCar(final CarDto car) {
    firePropertyChange("car", this.car, this.car = car);
  }
  
  /**
   * Returns the number property or <code>null</code> if not present.
   */
  public String getNumber() {
    checkDisposed();
    return this.number;
  }
  
  /**
   * Sets the <code>number</code> property to this instance.
   * 
   * @param number - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setNumber(final String number) {
    firePropertyChange("number", this.number, this.number = number );
  }
  
  /**
   * Returns the description property or <code>null</code> if not present.
   */
  public String getDescription() {
    checkDisposed();
    return this.description;
  }
  
  /**
   * Sets the <code>description</code> property to this instance.
   * 
   * @param description - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setDescription(final String description) {
    firePropertyChange("description", this.description, this.description = description );
  }
  
  /**
   * Returns the price property or <code>null</code> if not present.
   */
  public PriceDto getPrice() {
    checkDisposed();
    return this.price;
  }
  
  /**
   * Sets the <code>price</code> property to this instance.
   * 
   * @param price - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setPrice(final PriceDto price) {
    firePropertyChange("price", this.price, this.price = price );
  }
}
