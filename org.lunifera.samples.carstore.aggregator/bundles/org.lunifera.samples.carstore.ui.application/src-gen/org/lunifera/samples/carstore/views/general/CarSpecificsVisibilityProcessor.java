package org.lunifera.samples.carstore.views.general;

import org.lunifera.ecview.core.common.visibility.IVisibilityHandler;
import org.lunifera.ecview.core.common.visibility.IVisibilityManager;
import org.lunifera.ecview.core.common.visibility.IVisibilityProcessor;
import org.lunifera.samples.carstore.dtos.general.CarDto;
import org.lunifera.samples.carstore.dtos.general.ConvertibleDto;
import org.lunifera.samples.carstore.dtos.general.PickupDto;

@SuppressWarnings("all")
public class CarSpecificsVisibilityProcessor implements IVisibilityProcessor {
  private IVisibilityHandler pickupForm;
  
  private IVisibilityHandler convertibleForm;
  
  private CarDto main;
  
  public void init(final IVisibilityManager manager) {
    pickupForm = manager.getById("CarDetails.mainView.PickupForm");
    convertibleForm = manager.getById("CarDetails.mainView.ConvertibleForm");
  }
  
  public void fire() {
    doFire();
    
    pickupForm.apply();
    convertibleForm.apply();
  }
  
  public void doFire() {
    this.pickupForm.setVisible(false);
    this.convertibleForm.setVisible(false);
    if ((this.main instanceof PickupDto)) {
      this.pickupForm.setVisible(true);
    }
    if ((this.main instanceof ConvertibleDto)) {
      this.convertibleForm.setVisible(true);
    }
  }
  
  public CarDto getMain() {
    return this.main;
  }
  
  public void setMain(final CarDto main) {
    this.main=main;
    
    fire();
  }
}
