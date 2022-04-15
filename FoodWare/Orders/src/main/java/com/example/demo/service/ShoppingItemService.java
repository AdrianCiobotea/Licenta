package com.example.demo.service;

import com.example.demo.entity.ShoppingItem;
import com.example.demo.repository.ShoppingItemRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShoppingItemService {

  @Autowired
  ShoppingItemRepository shoppingItemRepository;

  public String insertShoppingItem(ShoppingItem shoppingItem) {
    try {
      shoppingItemRepository.save(shoppingItem);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid shopping item";
    }
    return "Successfully inserted an shopping item";
  }

  public Optional<ShoppingItem> loadShoppingItemById(Integer shoppingItemId) {
    return shoppingItemRepository.findById(shoppingItemId);
  }

  public String updateShoppingItem(ShoppingItem shoppingItem, Integer shoppingItemId) {
    try {
      ShoppingItem shoppingItemDB = loadShoppingItemById(shoppingItemId).get();
      if (shoppingItem.getShoppingCart_id() == 0) {
        shoppingItemDB.setShoppingCart_id(shoppingItemDB.getShoppingCart_id());
      } else {
        shoppingItemDB.setShoppingCart_id(shoppingItem.getShoppingCart_id());
      }
      if (shoppingItem.getParent_id() == 0) {
        shoppingItemDB.setParent_id(shoppingItemDB.getParent_id());
      } else {
        shoppingItemDB.setParent_id(shoppingItem.getParent_id());
      }
      if (shoppingItem.getQuantity() == 0) {
        shoppingItemDB.setQuantity(shoppingItemDB.getQuantity());
      } else {
        shoppingItemDB.setQuantity(shoppingItem.getQuantity());
      }
      if (shoppingItem.getProduct_id() == 0) {
        shoppingItemDB.setProduct_id(shoppingItemDB.getProduct_id());
      } else {
        shoppingItemDB.setProduct_id(shoppingItem.getProduct_id());
      }
      if (shoppingItem.getStatus_id() == 0) {
        shoppingItemDB.setStatus_id(shoppingItemDB.getStatus_id());
      } else {
        shoppingItemDB.setStatus_id(shoppingItem.getStatus_id());
      }
      if (shoppingItem.getUser_id() == 0) {
        shoppingItemDB.setUser_id(shoppingItemDB.getUser_id());
      } else {
        shoppingItemDB.setUser_id(shoppingItem.getUser_id());
      }

      shoppingItemRepository.save(shoppingItemDB);
    } catch (NoSuchElementException e) {
      if (shoppingItem.getShoppingCart_id() != 0
          && shoppingItem.getQuantity() != 0
          && shoppingItem.getParent_id() != 0
          && shoppingItem.getStatus_id() != 0
          && shoppingItem.getProduct_id() != 0
          && shoppingItem.getUser_id() != 0) {
        shoppingItemRepository.save(shoppingItem);
      } else {
        return "Please provide a valid shopping item";
      }
    } catch (IllegalArgumentException e) {
      return "Please provide a valid shopping item";
    }
    return "Successfully updated the shopping item";
  }

  public String deleteShoppingItemById(Integer shoppingItemId) {
    shoppingItemRepository.deleteById(shoppingItemId);
    return "Successfully deleted shopping item with id " + shoppingItemId;
  }

  public List<ShoppingItem> loadAllShoppingItems() {
    List<ShoppingItem> shoppingItems = new ArrayList<>();
    shoppingItemRepository.findAll().forEach(shoppingItems::add);
    return shoppingItems;
  }
}
