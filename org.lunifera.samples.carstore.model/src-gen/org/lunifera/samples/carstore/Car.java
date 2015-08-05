package org.lunifera.samples.carstore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lunifera.samples.carstore.ConfigDetail;
import org.lunifera.samples.carstore.Item;
import org.lunifera.samples.carstore.Price;
import org.lunifera.samples.carstore.Weight;

@Entity
@Table(name = "CAR")
@DiscriminatorValue(value = "CAR")
@SuppressWarnings("all")
public abstract class Car extends Item {
  @Column(name = "AXES")
  private int axes;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "amount", column = @Column(name = "WEIGHT_AMOUNT")), @AttributeOverride(name = "siType", column = @Column(name = "WEIGHT_SITYPE")) })
  @Column(name = "WEIGHT")
  private Weight weight;
  
  @Embedded
  @AttributeOverrides(value = { @AttributeOverride(name = "amount", column = @Column(name = "PRICE_AMOUNT")), @AttributeOverride(name = "currency", column = @Column(name = "PRICE_CURRENCY")) })
  @Column(name = "PRICE")
  private Price price;
  
  @JoinColumn(name = "CONFIG_DETAILS")
  @OneToMany(mappedBy = "car")
  private List<ConfigDetail> configDetails;
  
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
   * Returns the axes property or <code>null</code> if not present.
   */
  public int getAxes() {
    checkDisposed();
    return this.axes;
  }
  
  /**
   * Sets the axes property to this instance.
   */
  public void setAxes(final int axes) {
    checkDisposed();
    this.axes = axes;
  }
  
  /**
   * Returns the weight property or <code>null</code> if not present.
   */
  public Weight getWeight() {
    checkDisposed();
    return this.weight;
  }
  
  /**
   * Sets the weight property to this instance.
   */
  public void setWeight(final Weight weight) {
    checkDisposed();
    this.weight = weight;
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
   * Returns an unmodifiable list of configDetails.
   */
  public List<ConfigDetail> getConfigDetails() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetConfigDetails());
  }
  
  /**
   * Sets the given configDetails to the object. Currently contained configDetails instances will be removed.
   * 
   * @param configDetails the list of new instances
   */
  public void setConfigDetails(final List<ConfigDetail> configDetails) {
    // remove the old configDetail
    for(ConfigDetail oldElement : new ArrayList<ConfigDetail>(this.internalGetConfigDetails())){
      removeFromConfigDetails(oldElement);
    }
    
    // add the new configDetail
    for(ConfigDetail newElement : configDetails){
      addToConfigDetails(newElement);
    }
  }
  
  /**
   * Returns the list of <code>ConfigDetail</code>s thereby lazy initializing it.
   */
  private List<ConfigDetail> internalGetConfigDetails() {
    if (this.configDetails == null) {
      this.configDetails = new ArrayList<ConfigDetail>();
    }
    return this.configDetails;
  }
  
  /**
   * Adds the given configDetail to this object. <p>
   * Since the reference is a composition reference, the opposite reference (ConfigDetail.car)
   * of the configDetail will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link ConfigDetail#setCar(ConfigDetail)}.
   * 
   */
  public void addToConfigDetails(final ConfigDetail configDetail) {
    checkDisposed();
    configDetail.setCar(this);
  }
  
  /**
   * Removes the given configDetail from this object. <p>
   * 
   */
  public void removeFromConfigDetails(final ConfigDetail configDetail) {
    checkDisposed();
    configDetail.setCar(null);
  }
  
  void internalAddToConfigDetails(final ConfigDetail configDetail) {
    internalGetConfigDetails().add(configDetail);
  }
  
  void internalRemoveFromConfigDetails(final ConfigDetail configDetail) {
    internalGetConfigDetails().remove(configDetail);
  }
}
