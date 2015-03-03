package org.lunifera.samples.carstore.entities.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.general.Item;
import org.lunifera.samples.carstore.entities.general.Price;
import org.lunifera.samples.carstore.entities.general.Weight;

@Entity
@Table(name = "CAR")
@DiscriminatorValue(value = "CAR")
@SuppressWarnings("all")
public class Car extends Item {
  @Column(name = "AXES")
  private int axes;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "WEIGHT_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "uom", joinColumns = @JoinColumn(name = "WEIGHT_UOM")))
  @Column(name = "WEIGHT")
  private Weight weight;
  
  @Embedded
  @AttributeOverrides(value = @AttributeOverride(name = "amount", column = @Column(name = "PRICE_AMOUNT")))
  @AssociationOverrides(value = @AssociationOverride(name = "currency", joinColumns = @JoinColumn(name = "PRICE_CURRENCY")))
  @Column(name = "PRICE")
  private Price price;
  
  @JoinColumn(name = "CONFIG_DETAILS")
  @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ConfigDetailDefinition> configDetails;
  
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
        for (ConfigDetailDefinition configDetailDefinition : this.configDetails) {
          configDetailDefinition.dispose();
        }
        this.configDetails = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
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
  public List<ConfigDetailDefinition> getConfigDetails() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetConfigDetails());
  }
  
  /**
   * Sets the given configDetails to the object. Currently contained configDetails instances will be removed.
   * 
   * @param configDetails the list of new instances
   */
  public void setConfigDetails(final List<ConfigDetailDefinition> configDetails) {
    // remove the old configDetailDefinition
    for(ConfigDetailDefinition oldElement : new ArrayList<ConfigDetailDefinition>(this.internalGetConfigDetails())){
      removeFromConfigDetails(oldElement);
    }
    
    // add the new configDetailDefinition
    for(ConfigDetailDefinition newElement : configDetails){
      addToConfigDetails(newElement);
    }
  }
  
  /**
   * Returns the list of <code>ConfigDetailDefinition</code>s thereby lazy initializing it.
   */
  private List<ConfigDetailDefinition> internalGetConfigDetails() {
    if (this.configDetails == null) {
      this.configDetails = new ArrayList<ConfigDetailDefinition>();
    }
    return this.configDetails;
  }
  
  /**
   * Adds the given configDetailDefinition to this object. <p>
   * Since the reference is a composition reference, the opposite reference (ConfigDetailDefinition.car)
   * of the configDetailDefinition will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link ConfigDetailDefinition#setCar(ConfigDetailDefinition)}.
   * 
   */
  public void addToConfigDetails(final ConfigDetailDefinition configDetailDefinition) {
    checkDisposed();
    configDetailDefinition.setCar(this);
  }
  
  /**
   * Removes the given configDetailDefinition from this object. <p>
   * Since the reference is a cascading reference, the opposite reference (ConfigDetailDefinition.car)
   * of the configDetailDefinition will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link ConfigDetailDefinition#setCar(ConfigDetailDefinition)}.
   * 
   */
  public void removeFromConfigDetails(final ConfigDetailDefinition configDetailDefinition) {
    checkDisposed();
    configDetailDefinition.setCar(null);
  }
  
  /**
   * For internal use only!
   */
  void internalAddToConfigDetails(final ConfigDetailDefinition configDetailDefinition) {
    internalGetConfigDetails().add(configDetailDefinition);
  }
  
  /**
   * For internal use only!
   */
  void internalRemoveFromConfigDetails(final ConfigDetailDefinition configDetailDefinition) {
    internalGetConfigDetails().remove(configDetailDefinition);
  }
}
