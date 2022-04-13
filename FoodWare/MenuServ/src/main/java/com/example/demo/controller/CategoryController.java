package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
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
@RequestMapping(path = "category")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
  @Autowired
  CategoryService categoryService;

  @PostMapping(path = "insert")
  public String addCategory(@RequestBody Category category) {
    return categoryService.insertCategory(category);
  }

  @GetMapping()
  public List<Category> getAllCategories(@RequestParam(value = "groupId", required = false) Optional<Integer> groupId) {
    if (groupId.isPresent()) {
      return categoryService.loadAllCategoriesByGroupId(groupId.get());
    } else {
      return categoryService.loadAllCategories();
    }
  }

  @GetMapping(path = "/{id}")
  public java.util.Optional<Category> getCategoryById(@PathVariable Integer id) {
    return categoryService.loadCategoryById(id);
  }

  @PostMapping(path = "update/{id}")
  public String updateCategory(@PathVariable Integer id, @RequestBody Category category) {
    return categoryService.updateCategory(category, id);
  }

  @PostMapping(path = "delete/{id}")
  public String deleteCategory(@PathVariable Integer id) {
    return categoryService.deleteCategoryById(id);
  }
}
