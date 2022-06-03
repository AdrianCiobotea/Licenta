package com.example.demo.controller;

import com.example.demo.entity.SubOrder;
import com.example.demo.service.SubOrderService;
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
@RequestMapping(path = "subOrder")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class SubOrderController {

  @Autowired
  SubOrderService subOrderService;

  @PostMapping(path = "insert")
  public Optional<SubOrder> addSubOrder(@RequestBody SubOrder subOrder) {
    return subOrderService.insertSubOrder(subOrder);
  }

  @GetMapping()
  public List<SubOrder> getAllSubOrders() {
    return subOrderService.loadAllSubOrders();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<SubOrder> getSubOrderById(@PathVariable Integer id) {
    return subOrderService.loadSubOrderById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateSubOrder(@PathVariable Integer id, @RequestBody SubOrder subOrder) {
    return subOrderService.updateSubOrder(subOrder, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteSubOrder(@PathVariable Integer id) {
    return subOrderService.deleteSubOrderById(id);
  }
}
