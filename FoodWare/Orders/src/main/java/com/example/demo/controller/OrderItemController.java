package com.example.demo.controller;

import com.example.demo.entity.OrderItem;
import com.example.demo.service.OrderItemService;
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
public class OrderItemController {
  @Autowired
  OrderItemService shoppingItemService;

  @PostMapping(path = "insert")
  public String addOrderItem(@RequestBody OrderItem shoppingItem) {
    return shoppingItemService.insertOrderItem(shoppingItem);
  }

  @GetMapping()
  public List<OrderItem> getAllOrderItems() {
    return shoppingItemService.loadAllOrderItems();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<OrderItem> getOrderItemById(@PathVariable Integer id) {
    return shoppingItemService.loadOrderItemById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateOrderItem(@PathVariable Integer id, @RequestBody OrderItem shoppingItem) {
    return shoppingItemService.updateOrderItem(shoppingItem, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteOrderItem(@PathVariable Integer id) {
    return shoppingItemService.deleteOrderItemById(id);
  }
}
