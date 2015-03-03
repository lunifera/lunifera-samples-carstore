package org.lunifera.samples.carstore.dtos;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.ConfigDetailDto;
import org.lunifera.samples.carstore.dtos.ItemDto;
import org.lunifera.samples.carstore.dtos.PriceDto;
import org.lunifera.samples.carstore.dtos.WeightDto;

@SuppressWarnings("all")
public class CarDto extends ItemDto implements Serializable {
  private int axes;
  
  private WeightDto weight;
  
  private PriceDto price;
  
  @DomainReference
  private List<ConfigDetailDto> configDetails;
  
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
   * Sets the <code>axes</code> property to this instance.
   * 
   * @param axes - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setAxes(final int axes) {
    firePropertyChange("axes", this.axes, this.axes = axes );
  }
  
  /**
   * Returns the weight property or <code>null</code> if not present.
   */
  public WeightDto getWeight() {
    checkDisposed();
    return this.weight;
  }
  
  /**
   * Sets the <code>weight</code> property to this instance.
   * 
   * @param weight - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setWeight(final WeightDto weight) {
    firePropertyChange("weight", this.weight, this.weight = weight );
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
  
  /**
   * Returns an unmodifiable list of configDetails.
   */
  public List<ConfigDetailDto> getConfigDetails() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetConfigDetails());
  }
  
  /**
   * Returns the list of <code>ConfigDetailDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<ConfigDetailDto> internalGetConfigDetails() {
    if (this.configDetails == null) {
      this.configDetails = new java.util.ArrayList<ConfigDetailDto>();
    }
    return this.configDetails;
  }
  
  /**
   * Adds the given configDetailDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>ConfigDetailDto#car</code> of the <code>configDetailDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link ConfigDetailDto#setCar(ConfigDetailDto)}.
   * 
   * @param configDetailDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToConfigDetails(final ConfigDetailDto configDetailDto) {
    checkDisposed();
    configDetailDto.setCar(this);
  }
  
  /**
   * Removes the given configDetailDto from this object. <p>
   * 
   * @param configDetailDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromConfigDetails(final ConfigDetailDto configDetailDto) {
    checkDisposed();
    configDetailDto.setCar(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToConfigDetails(final ConfigDetailDto configDetailDto) {
    internalGetConfigDetails().add(configDetailDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromConfigDetails(final ConfigDetailDto configDetailDto) {
    internalGetConfigDetails().remove(configDetailDto);
  }
  
  /**
   * Sets the <code>configDetails</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>ConfigDetailDto#
   * car</code> of the <code>configDetails</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link ConfigDetailDto#setCar(ConfigDetailDto)
   * 
   * @param configDetails - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setConfigDetails(final List<ConfigDetailDto> configDetails) {
    checkDisposed();
    for (ConfigDetailDto dto : internalGetConfigDetails().toArray(new ConfigDetailDto[this.configDetails.size()])) {
      removeFromConfigDetails(dto);
    }
    if(configDetails == null){
      return;
    }
    for (ConfigDetailDto dto : configDetails) {
      addToConfigDetails(dto);
    }
    
  }
}
