package org.lunifera.samples.carstore.dtos.sales.services;

import org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService;
import org.lunifera.samples.carstore.dtos.sales.InventoryDto;
import org.lunifera.samples.carstore.entities.sales.Inventory;

@SuppressWarnings("all")
public class InventoryService extends AbstractDTOService<InventoryDto, Inventory> {
  public Class<InventoryDto> getDtoClass() {
    return InventoryDto.class;
  }
  
  public InventoryDto createDto() {
    return new InventoryDto();
  }
  
  public Inventory createEntity() {
    return new Inventory();
  }
  
  public Class<Inventory> getEntityClass() {
    return Inventory.class;
  }
  
  public Object getId(final InventoryDto dto) {
    return dto.getId();
  }
}
