package org.lunifera.samples.carstore.entities.general;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.CascadeRoot;

@Entity
@Table(name = "CASCADE_YES")
@DiscriminatorValue(value = "CASCADE_YES")
@SuppressWarnings("all")
public class CascadeYes implements IEntity {
  @Transient
  @Dispose
  private boolean disposed;
  
  @Id
  private String id = java.util.UUID.randomUUID().toString();
  
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "ROOT_ID")
  private CascadeRoot root;
  
  /**
   * Returns true, if the object is disposed. 
   * Disposed means, that it is prepared for garbage collection and may not be used anymore. 
   * Accessing objects that are already disposed will cause runtime exceptions.
   */
  @Dispose
  public boolean isDisposed() {
    return this.disposed;
  }
  
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
      if (this.root != null) {
        this.root.dispose();
        this.root = null;
      }
      
    }
    finally {
      disposed = true;
    }
    
  }
  
  /**
   * Returns the id property or <code>null</code> if not present.
   */
  public String getId() {
    checkDisposed();
    return this.id;
  }
  
  /**
   * Sets the id property to this instance.
   */
  public void setId(final String id) {
    checkDisposed();
    this.id = id;
  }
  
  /**
   * Returns the root property or <code>null</code> if not present.
   */
  public CascadeRoot getRoot() {
    checkDisposed();
    return this.root;
  }
  
  /**
   * Sets the root property to this instance.
   * Since the reference is a container reference, the opposite reference (CascadeRoot.refYes)
   * of the root will be handled automatically and no further coding is required to keep them in sync.
   * See {@link CascadeRoot#setRefYes(CascadeRoot)}.
   */
  public void setRoot(final CascadeRoot root) {
    checkDisposed();
    if (this.root != null) {
      this.root.internalRemoveFromRefYes(this);
    }
    this.root = root;
    if (this.root != null) {
      this.root.internalAddToRefYes(this);
    }
    
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CascadeYes other = (CascadeYes) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    return true;
  }
  
  @Override
  public int hashCode() {
     int prime = 31;
    int result = 1;
    result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
    return result;
  }
}
