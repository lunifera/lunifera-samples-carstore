package org.lunifera.samples.carstore.dtos.general;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.lunifera.dsl.dto.lib.MappingContext;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.runtime.common.annotations.DomainReference;
import org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto;
import org.lunifera.samples.carstore.dtos.general.ItemDto;
import org.lunifera.samples.carstore.dtos.general.PriceDto;
import org.lunifera.samples.carstore.dtos.general.WeightDto;

@SuppressWarnings("all")
public class CarDto extends ItemDto implements Serializable, PropertyChangeListener {
  private int axes;
  
  private WeightDto weight;
  
  private PriceDto price;
  
  @DomainReference
  private List<ConfigDetailDefinitionDto> configDetails;
  
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
        for (ConfigDetailDefinitionDto configDetailDefinitionDto : this.configDetails) {
          configDetailDefinitionDto.dispose();
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
   * Returns the weight property.
   */
  public WeightDto getWeight() {
    if(this.weight== null){
      this.weight = new WeightDto();
    }
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
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.weight != null) {
    	this.weight.removePropertyChangeListener(this);
    }
    
    firePropertyChange("weight", this.weight, this.weight = weight );
    
    if (this.weight != null) {
    	this.weight.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns the price property.
   */
  public PriceDto getPrice() {
    if(this.price== null){
      this.price = new PriceDto();
    }
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
    // ensure that embedded beans will notify their parent about changes
    // so their dirty state can be handled properly
    if (this.price != null) {
    	this.price.removePropertyChangeListener(this);
    }
    
    firePropertyChange("price", this.price, this.price = price );
    
    if (this.price != null) {
    	this.price.addPropertyChangeListener(this);
    }
  }
  
  /**
   * Returns an unmodifiable list of configDetails.
   */
  public List<ConfigDetailDefinitionDto> getConfigDetails() {
    return Collections.unmodifiableList(internalGetConfigDetails());
  }
  
  /**
   * Returns the list of <code>ConfigDetailDefinitionDto</code>s thereby lazy initializing it. For internal use only!
   * 
   * @return list - the resulting list
   * 
   */
  private List<ConfigDetailDefinitionDto> internalGetConfigDetails() {
    if (this.configDetails == null) {
      this.configDetails = new java.util.ArrayList<ConfigDetailDefinitionDto>();
    }
    return this.configDetails;
  }
  
  /**
   * Adds the given configDetailDefinitionDto to this object. <p>
   * Since the reference is a composition reference, the opposite reference <code>ConfigDetailDefinitionDto#car</code> of the <code>configDetailDefinitionDto</code> will be handled automatically and no further coding is required to keep them in sync.<p>
   * See {@link ConfigDetailDefinitionDto#setCar(ConfigDetailDefinitionDto)}.
   * 
   * @param configDetailDefinitionDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void addToConfigDetails(final ConfigDetailDefinitionDto configDetailDefinitionDto) {
    checkDisposed();
    
    configDetailDefinitionDto.setCar(this);
  }
  
  /**
   * Removes the given configDetailDefinitionDto from this object. <p>
   * 
   * @param configDetailDefinitionDto - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void removeFromConfigDetails(final ConfigDetailDefinitionDto configDetailDefinitionDto) {
    checkDisposed();
    
    configDetailDefinitionDto.setCar(null);
  }
  
  /**
   * For internal use only!
   */
  public void internalAddToConfigDetails(final ConfigDetailDefinitionDto configDetailDefinitionDto) {
    internalGetConfigDetails().add(configDetailDefinitionDto);
  }
  
  /**
   * For internal use only!
   */
  public void internalRemoveFromConfigDetails(final ConfigDetailDefinitionDto configDetailDefinitionDto) {
    internalGetConfigDetails().remove(configDetailDefinitionDto);
  }
  
  /**
   * Sets the <code>configDetails</code> property to this instance.
   * Since the reference has an opposite reference, the opposite <code>ConfigDetailDefinitionDto#
   * car</code> of the <code>configDetails</code> will be handled automatically and no 
   * further coding is required to keep them in sync.<p>
   * See {@link ConfigDetailDefinitionDto#setCar(ConfigDetailDefinitionDto)
   * 
   * @param configDetails - the property
   * @throws RuntimeException if instance is <code>disposed</code>
   * 
   */
  public void setConfigDetails(final List<ConfigDetailDefinitionDto> configDetails) {
    checkDisposed();
    for (ConfigDetailDefinitionDto dto : internalGetConfigDetails().toArray(new ConfigDetailDefinitionDto[this.configDetails.size()])) {
    	removeFromConfigDetails(dto);
    }
    
    if(configDetails == null) {
    	return;
    }
    
    for (ConfigDetailDefinitionDto dto : configDetails) {
    	addToConfigDetails(dto);
    }
  }
  
  public CarDto createDto() {
    return new CarDto();
  }
  
  public CarDto copy(final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    if(context.isMaxLevel()){
    	return null;
    }
    
    // if context contains a copied instance of this object
    // then return it
    CarDto newDto = context.get(this);
    if(newDto != null){
    	return newDto;
    }
    
    try{
    	context.increaseLevel();
    	
    	newDto = createDto();
    	context.register(this, newDto);
    	
    	// first copy the containments and attributes
    	copyContainments(this, newDto, context);
    	
    	// then copy cross references to ensure proper
    	// opposite references are copied too.
    	copyCrossReferences(this, newDto, context);
    } finally {
    	context.decreaseLevel();
    }
    
    return newDto;
  }
  
  public void copyContainments(final CarDto dto, final CarDto newDto, final MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyContainments(dto, newDto, context);
    
    // copy attributes and beans (beans if derived from entity model)
    // copy axes
    newDto.setAxes(getAxes());
    // copy dto weight
    if(getWeight() != null) {
    	newDto.setWeight(getWeight().copy(context));
    }
    // copy dto price
    if(getPrice() != null) {
    	newDto.setPrice(getPrice().copy(context));
    }
    
    // copy containment references (cascading is true)
    // copy list of configDetails dtos
    for(org.lunifera.samples.carstore.dtos.general.ConfigDetailDefinitionDto _dto : getConfigDetails()) {
    	newDto.addToConfigDetails(_dto.copy(context));
    }
  }
  
  public void copyCrossReferences(final CarDto dto, final CarDto newDto, final org.lunifera.dsl.dto.lib.MappingContext context) {
    checkDisposed();
    
    if (context == null) {
    	throw new IllegalArgumentException("Context must not be null!");
    }
    
    super.copyCrossReferences(dto, newDto, context);
    
    // copy cross references (cascading is false)
  }
  
  public void propertyChange(final java.beans.PropertyChangeEvent event) {
    Object source = event.getSource();
    
    // forward the event from embeddable beans to all listeners. So the parent of the embeddable
    // bean will become notified and its dirty state can be handled properly
    if(source == weight){
    	firePropertyChange("weight" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    if(source == price){
    	firePropertyChange("price" + "_" + event.getPropertyName(), event.getOldValue(), event.getNewValue());
    } else 
    { 
    	super.propertyChange(event);
    }
  }
}
