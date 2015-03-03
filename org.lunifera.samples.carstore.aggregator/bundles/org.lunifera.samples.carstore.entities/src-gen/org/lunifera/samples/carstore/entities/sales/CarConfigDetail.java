package org.lunifera.samples.carstore.entities.sales;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.Base;
import org.lunifera.samples.carstore.entities.general.ConfigDetailDefinition;
import org.lunifera.samples.carstore.entities.sales.SalesOrderDetail;

@Entity
@Table(name = "CAR_CONFIG_DETAIL")
@SuppressWarnings("all")
public class CarConfigDetail extends Base {
  @Column(name = "NUMBER")
  private int number;
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "PARENT_ID")
  private SalesOrderDetail parent;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CONFIG_DEF_ID")
  private ConfigDetailDefinition configDef;
  
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
      if (this.parent != null) {
        this.parent.dispose();
        this.parent = null;
      }
      
    }
    finally {
      super.dispose();
    }
    
  }
  
  /**
   * Returns the number property or <code>null</code> if not present.
   */
  public int getNumber() {
    checkDisposed();
    return this.number;
  }
  
  /**
   * Sets the number property to this instance.
   */
  public void setNumber(final int number) {
    checkDisposed();
    this.number = number;
  }
  
  /**
   * Returns the parent property or <code>null</code> if not present.
   */
  public SalesOrderDetail getParent() {
    checkDisposed();
    return this.parent;
  }
  
  /**
   * Sets the parent property to this instance.
   * Since the reference is a container reference, the opposite reference (SalesOrderDetail.configDetails)
   * of the parent will be handled automatically and no further coding is required to keep them in sync.
   * See {@link SalesOrderDetail#setConfigDetails(SalesOrderDetail)}.
   */
  public void setParent(final SalesOrderDetail parent) {
    checkDisposed();
    if (this.parent != null) {
      this.parent.internalRemoveFromConfigDetails(this);
    }
    this.parent = parent;
    if (this.parent != null) {
      this.parent.internalAddToConfigDetails(this);
    }
    
  }
  
  /**
   * Returns the configDef property or <code>null</code> if not present.
   */
  public ConfigDetailDefinition getConfigDef() {
    checkDisposed();
    return this.configDef;
  }
  
  /**
   * Sets the configDef property to this instance.
   */
  public void setConfigDef(final ConfigDetailDefinition configDef) {
    checkDisposed();
    this.configDef = configDef;
  }
}
