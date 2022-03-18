package com.example.demo.controller;

import com.example.demo.dto.CategoryDetails;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "category")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @PostMapping(path = "insert")
  public String addCategory(@RequestBody Category category) {
    return categoryService.insertCategory(category);
  }

  @GetMapping()
  public List<CategoryDetails> getAllCategories() {
    return categoryService.loadAllCategories();
  }
}
