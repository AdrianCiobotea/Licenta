package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<Category> getAllCategories() {
        return categoryService.loadAllCategories();
    }

    @GetMapping(path = "/{id}")
    public java.util.Optional<Category> getCategoryById(@PathVariable Integer id) {
        return categoryService.loadCategoryById(id);
    }

    @PostMapping(path = "delete/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategoryById(id);
    }
}
