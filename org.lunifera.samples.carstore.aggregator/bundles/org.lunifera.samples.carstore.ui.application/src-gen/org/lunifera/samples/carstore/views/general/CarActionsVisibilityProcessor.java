package org.lunifera.samples.carstore.views.general;

import com.google.common.base.Objects;
import org.lunifera.ecview.core.common.visibility.IVisibilityHandler;
import org.lunifera.ecview.core.common.visibility.IVisibilityManager;
import org.lunifera.ecview.core.common.visibility.IVisibilityProcessor;
import org.lunifera.samples.carstore.dtos.general.CarDto;

@SuppressWarnings("all")
public class CarActionsVisibilityProcessor implements IVisibilityProcessor {
  private IVisibilityHandler reloadAction;
  
  private IVisibilityHandler deleteAction;
  
  private IVisibilityHandler saveAction;
  
  private IVisibilityHandler validateAction;
  
  private IVisibilityHandler mainLayout;
  
  private CarDto main;
  
  private boolean dirty;
  
  private int axes;
  
  public void init(final IVisibilityManager manager) {
    reloadAction = manager.getById("org.lunifera.actions.load");
    deleteAction = manager.getById("org.lunifera.actions.delete");
    saveAction = manager.getById("org.lunifera.actions.save");
    validateAction = manager.getById("org.lunifera.actions.validate");
    mainLayout = manager.getById("CarDetails.mainView");
  }
  
  public void fire() {
    doFire();
    
    reloadAction.apply();
    deleteAction.apply();
    saveAction.apply();
    validateAction.apply();
    mainLayout.apply();
  }
  
  public void doFire() {
    this.mainLayout.setEnabled(true);
    this.reloadAction.setEnabled(false);
    this.deleteAction.setEnabled(false);
    this.saveAction.setEnabled(false);
    this.validateAction.setEnabled(false);
    boolean _notEquals = (!Objects.equal(this.main, null));
    if (_notEquals) {
      if (this.dirty) {
        this.reloadAction.setEnabled(true);
        this.saveAction.setEnabled(true);
      }
      this.deleteAction.setEnabled(true);
      this.validateAction.setEnabled(true);
    } else {
      this.mainLayout.setEnabled(false);
    }
  }
  
  public CarDto getMain() {
    return this.main;
  }
  
  public void setMain(final CarDto main) {
    this.main=main;
    
    fire();
  }
  
  public boolean isDirty() {
    return this.dirty;
  }
  
  public void setDirty(final boolean dirty) {
    this.dirty=dirty;
    
    fire();
  }
  
  public int getAxes() {
    return this.axes;
  }
  
  public void setAxes(final int axes) {
    this.axes=axes;
    
    fire();
  }
}
