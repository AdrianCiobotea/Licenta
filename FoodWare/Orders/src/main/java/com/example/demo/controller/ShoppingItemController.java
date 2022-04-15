package com.example.demo.controller;

import com.example.demo.entity.ShoppingItem;
import com.example.demo.service.ShoppingItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "shoppingItem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ShoppingItemController {
  @Autowired
  ShoppingItemService shoppingItemService;

  @PostMapping(path = "insert")
  public String addShoppingItem(@RequestBody ShoppingItem shoppingItem) {
    return shoppingItemService.insertShoppingItem(shoppingItem);
  }

  @GetMapping()
  public List<ShoppingItem> getAllShoppingItems() {
    return shoppingItemService.loadAllShoppingItems();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<ShoppingItem> getShoppingItemById(@PathVariable Integer id) {
    return shoppingItemService.loadShoppingItemById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateShoppingItem(@PathVariable Integer id, @RequestBody ShoppingItem shoppingItem) {
    return shoppingItemService.updateShoppingItem(shoppingItem, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteShoppingItem(@PathVariable Integer id) {
    return shoppingItemService.deleteShoppingItemById(id);
  }
}
