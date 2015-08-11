package org.lunifera.samples.carstore.entities.general;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import org.lunifera.dsl.common.datatypes.IEntity;
import org.lunifera.runtime.common.annotations.Dispose;

/**
 * The mapped superclass providing an UUID.
 */
@MappedSuperclass
@SuppressWarnings("all")
public class Base implements IEntity {
  @Transient
  @Dispose
  private boolean disposed;
  
  @Id
  private String id = java.util.UUID.randomUUID().toString();
  
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
    disposed = true;
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
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Base other = (Base) obj;
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
