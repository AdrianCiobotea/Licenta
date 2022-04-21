package com.example.demo.controller;

import com.example.demo.entity.Extra;
import com.example.demo.service.ExtraService;
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
@RequestMapping(path = "extra")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ExtraController {

  @Autowired
  ExtraService extraService;

  @PostMapping(path = "insert")
  public String addExtra(@RequestBody Extra extra) {
    return extraService.insertExtra(extra);
  }

  @GetMapping()
  public List<Extra> getAllExtraProducts() {
    return extraService.loadAllExtraProducts();
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Extra> getExtraById(@PathVariable Integer id) {
    return extraService.loadExtraById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateExtra(@PathVariable Integer id, @RequestBody Extra extra) {
    return extraService.updateExtra(extra, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteExtra(@PathVariable Integer id) {
    return extraService.deleteExtraById(id);
  }

}
