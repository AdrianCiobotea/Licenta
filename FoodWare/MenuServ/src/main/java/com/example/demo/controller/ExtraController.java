package com.example.demo.controller;

import com.example.demo.entity.Extra;
import com.example.demo.service.ExtraService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public List<Extra> getAllExtras(@RequestParam(value = "categoryId", required = false) Optional<Integer> categoryId) {
  if (categoryId.isPresent()) {
    return extraService.loadExtrasByCategoryId(categoryId.get());
  } else {
    return extraService.loadAllExtraProducts();
  }
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
