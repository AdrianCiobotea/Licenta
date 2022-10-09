package com.example.demo.controller;

import com.example.demo.entity.ExtraOrderItem;
import com.example.demo.service.ExtraOrderItemService;
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
@RequestMapping(path = "extraOrderItem")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExtraOrderItemController {
  @Autowired
  ExtraOrderItemService extraOrderItemService;

  @PostMapping(path = "insert")
  public Optional<ExtraOrderItem> addExtraOrderItem(@RequestBody ExtraOrderItem extraOrderItem) {

    return extraOrderItemService.insertExtraOrderItem(extraOrderItem);
  }

  @GetMapping()
  public List<ExtraOrderItem> getAllOrderItems() {
    return extraOrderItemService.loadAllExtraOrderItems();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<ExtraOrderItem> getExtraOrderItemById(@PathVariable Integer id) {
    return extraOrderItemService.loadExtraOrderItemById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateExtraOrderItem(@PathVariable Integer id, @RequestBody ExtraOrderItem extraOrderItem) {
    return extraOrderItemService.updateExtraOrderItem(extraOrderItem, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteExtraOrderItem(@PathVariable Integer id) {
    return extraOrderItemService.deleteExtraOrderItemById(id);
  }
}
