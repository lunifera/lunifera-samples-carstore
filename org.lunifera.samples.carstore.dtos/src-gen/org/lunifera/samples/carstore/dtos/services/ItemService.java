package org.lunifera.samples.carstore.dtos.services;

import org.lunifera.samples.carstore.Item;
import org.lunifera.samples.carstore.dtos.ItemDto;

@SuppressWarnings("all")
public class ItemService implements org.lunifera.dsl.dto.lib.services.impl.AbstractDTOService {
  public Class<ItemDto> getDtoClass() {
    return ItemDto.class;
  }
  
  public ItemDto createDto() {
    return new ItemDto();
  }
  
  public Item createEntity() {
    return new Item();
  }
  
  public Class<Item> getEntityClass() {
    return Item.class;
  }
  
  public Object getId(final ItemDto dto) {
    return dto.getId();
  }
}
