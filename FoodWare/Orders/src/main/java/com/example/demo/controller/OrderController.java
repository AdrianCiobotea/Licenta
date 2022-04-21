package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "shoppingCart")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

  @Autowired
  OrderService shoppingCartService;

  @PostMapping(path = "insert")
  public Optional<Order> addOrder(@RequestBody Order Order) {
    return shoppingCartService.insertOrder(Order);
  }

  @GetMapping()
  public List<Order> getAllOrders() {
    return shoppingCartService.loadAllOrders();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Order> getOrderById(@PathVariable Integer id) {
    return shoppingCartService.loadOrderById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateOrder(@PathVariable Integer id, @RequestBody Order Order) {
    return shoppingCartService.updateOrder(Order, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteOrder(@PathVariable Integer id) {
    return shoppingCartService.deleteOrderById(id);
  }

}
