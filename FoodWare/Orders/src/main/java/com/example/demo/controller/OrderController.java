package com.example.demo.controller;


import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "order")
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping(path = "insert")
  public String addOrder(@RequestBody Order order) {
    return orderService.insertOrder(order);
  }

  @GetMapping()
  public List<Order> getAllOrders() {
    return orderService.loadAllOrders();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Order> getOrderById(@PathVariable Integer id) {
    return orderService.loadOrderById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateProduct(@PathVariable Integer id, @RequestBody Order order) {
    return orderService.updateOrder(order, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteOrder(@PathVariable Integer id) {
    return orderService.deleteOrderById(id);
  }

}
