package com.example.demo.service;

import com.example.demo.dto.CategoryDetails;
import com.example.demo.entity.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Group;
import com.example.demo.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private GroupService groupService;

    @Value("${spring.datasource.password}")
    private String databasePassword;
    @Value("${spring.datasource.username}")
    private String databaseUserName;
    @Value("${spring.datasource.url}")
    private String databaseUrl;


    public String insertCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid category";
        }
        return "Successfully inserted a category";
    }

    public List<Category> loadAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    public Optional<Category> loadCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    public void deleteCategoryById(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    public String updateCategory(Category category) {
        try {
            categoryRepository.save(category);
        } catch (IllegalArgumentException e) {
            return "Please provide a valid category";
        }
        return "Successfully updated the category";
    }
}
