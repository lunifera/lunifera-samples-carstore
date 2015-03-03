package org.lunifera.samples.carstore.entities.general;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.lunifera.runtime.common.annotations.Dispose;
import org.lunifera.samples.carstore.entities.general.CascadeNo;
import org.lunifera.samples.carstore.entities.general.CascadeYes;

@Entity
@Table(name = "CASCADE_ROOT")
@DiscriminatorValue(value = "CASCADE_ROOT")
@SuppressWarnings("all")
public class CascadeRoot {
  @Transient
  @Dispose
  private boolean disposed;
  
  @Id
  private String id = java.util.UUID.randomUUID().toString();
  
  @JoinColumn(name = "REF_YES")
  @OneToMany(mappedBy = "root", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<CascadeYes> refYes;
  
  @JoinColumn(name = "REF_NO")
  @OneToMany(mappedBy = "root")
  private List<CascadeNo> refNo;
  
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
      if (this.refYes != null) {
        for (CascadeYes cascadeYes : this.refYes) {
          cascadeYes.dispose();
        }
        this.refYes = null;
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
   * Returns an unmodifiable list of refYes.
   */
  public List<CascadeYes> getRefYes() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetRefYes());
  }
  
  /**
   * Sets the given refYes to the object. Currently contained refYes instances will be removed.
   * 
   * @param refYes the list of new instances
   */
  public void setRefYes(final List<CascadeYes> refYes) {
    // remove the old cascadeYes
    for(CascadeYes oldElement : new ArrayList<CascadeYes>(this.internalGetRefYes())){
      removeFromRefYes(oldElement);
    }
    
    // add the new cascadeYes
    for(CascadeYes newElement : refYes){
      addToRefYes(newElement);
    }
  }
  
  /**
   * Returns the list of <code>CascadeYes</code>s thereby lazy initializing it.
   */
  private List<CascadeYes> internalGetRefYes() {
    if (this.refYes == null) {
      this.refYes = new ArrayList<CascadeYes>();
    }
    return this.refYes;
  }
  
  /**
   * Adds the given cascadeYes to this object. <p>
   * Since the reference is a composition reference, the opposite reference (CascadeYes.root)
   * of the cascadeYes will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link CascadeYes#setRoot(CascadeYes)}.
   * 
   */
  public void addToRefYes(final CascadeYes cascadeYes) {
    checkDisposed();
    cascadeYes.setRoot(this);
  }
  
  /**
   * Removes the given cascadeYes from this object. <p>
   * Since the reference is a cascading reference, the opposite reference (CascadeYes.root)
   * of the cascadeYes will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link CascadeYes#setRoot(CascadeYes)}.
   * 
   */
  public void removeFromRefYes(final CascadeYes cascadeYes) {
    checkDisposed();
    cascadeYes.setRoot(null);
  }
  
  /**
   * For internal use only!
   */
  void internalAddToRefYes(final CascadeYes cascadeYes) {
    internalGetRefYes().add(cascadeYes);
  }
  
  /**
   * For internal use only!
   */
  void internalRemoveFromRefYes(final CascadeYes cascadeYes) {
    internalGetRefYes().remove(cascadeYes);
  }
  
  /**
   * Returns an unmodifiable list of refNo.
   */
  public List<CascadeNo> getRefNo() {
    checkDisposed();
    return Collections.unmodifiableList(internalGetRefNo());
  }
  
  /**
   * Sets the given refNo to the object. Currently contained refNo instances will be removed.
   * 
   * @param refNo the list of new instances
   */
  public void setRefNo(final List<CascadeNo> refNo) {
    // remove the old cascadeNo
    for(CascadeNo oldElement : new ArrayList<CascadeNo>(this.internalGetRefNo())){
      removeFromRefNo(oldElement);
    }
    
    // add the new cascadeNo
    for(CascadeNo newElement : refNo){
      addToRefNo(newElement);
    }
  }
  
  /**
   * Returns the list of <code>CascadeNo</code>s thereby lazy initializing it.
   */
  private List<CascadeNo> internalGetRefNo() {
    if (this.refNo == null) {
      this.refNo = new ArrayList<CascadeNo>();
    }
    return this.refNo;
  }
  
  /**
   * Adds the given cascadeNo to this object. <p>
   * Since the reference is a composition reference, the opposite reference (CascadeNo.root)
   * of the cascadeNo will be handled automatically and no further coding is required to keep them in sync. 
   * See {@link CascadeNo#setRoot(CascadeNo)}.
   * 
   */
  public void addToRefNo(final CascadeNo cascadeNo) {
    checkDisposed();
    cascadeNo.setRoot(this);
  }
  
  /**
   * Removes the given cascadeNo from this object. <p>
   * 
   */
  public void removeFromRefNo(final CascadeNo cascadeNo) {
    checkDisposed();
    cascadeNo.setRoot(null);
  }
  
  /**
   * For internal use only!
   */
  void internalAddToRefNo(final CascadeNo cascadeNo) {
    internalGetRefNo().add(cascadeNo);
  }
  
  /**
   * For internal use only!
   */
  void internalRemoveFromRefNo(final CascadeNo cascadeNo) {
    internalGetRefNo().remove(cascadeNo);
  }
  
  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CascadeRoot other = (CascadeRoot) obj;
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
