package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repository.BaseCategoryRepository;
import com.example.demo.repository.CategoryRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

  @Autowired
  BaseCategoryRepository baseCategoryRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  private GroupService groupService;

  @Autowired
  private ProductService productService;

  @Value("${spring.datasource.password}")
  private String databasePassword;

  @Value("${spring.datasource.username}")
  private String databaseUserName;

  @Value("${spring.datasource.url}")
  private String databaseUrl;


  public String insertCategory(Category category) {
    try {
      baseCategoryRepository.save(category);
    } catch (IllegalArgumentException e) {
      return "Please provide a valid category";
    }
    return "Successfully inserted a category";
  }

  public List<Category> loadAllCategories() {
    List<Category> categories = new ArrayList<>();
    baseCategoryRepository.findAll().forEach(categories::add);
    return categories;
  }

  public List<Category> loadAllCategoriesByGroupId(int groupId) {
    return new ArrayList<>(categoryRepository.findCategoriesByGroupId(groupId));
  }

  public Optional<Category> loadCategoryById(int categoryId) {
    return baseCategoryRepository.findById(categoryId);
  }

  public String deleteCategoryById(int categoryId) {
    boolean existProductForCategoryId = false;
    List<Product> products = productService.loadAllProducts();
    for (Product product : products) {
      if (product.getCategory().getId() == categoryId) {
        existProductForCategoryId = true;
        break;
      }
    }
    if (existProductForCategoryId) {
      return "Cannot delete category while products reference it";
    } else {
      baseCategoryRepository.deleteById(categoryId);
      return "Successfully deleted the category: " + categoryId;
    }

  }

  public String updateCategory(Category category, int id) {

    if (loadCategoryById(id).isPresent()) {
      try {
        Category categoryDB = loadCategoryById(id).get();
        categoryDB.setName(category.getName());
        categoryDB.setGroup(category.getGroup());
        baseCategoryRepository.save(categoryDB);
      } catch (NoSuchElementException e) {
        if (category.getName() != null && !Objects.equals(category.getName(), "") && category.getGroup() != null) {
          baseCategoryRepository.save(category);
        } else {
          return "Please provide a valid category";
        }
      } catch (IllegalArgumentException e) {
        return "Please provide a valid category";
      }
      return "Successfully updated the category";
    } else {
      return "No such category found";
    }
  }

}
