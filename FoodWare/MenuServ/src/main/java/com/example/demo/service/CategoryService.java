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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

  @Autowired
  private GroupService groupService;

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  public CategoryDetails loadCategoryById(int categoryId) {
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select name,group_id from foodware.category where id=?");) {
      selectStatement.setInt(1, categoryId);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String categoryName = rs.getString("name");
        int groupId = rs.getInt("group_id");

        CategoryDetails category = new CategoryDetails();
        category.setName(categoryName);
        category.setGroup(groupService.loadGroupById(groupId));

        return category;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return null;
  }

  public CategoryDetails loadCategoryByName(String categoryName) {
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select name,id from foodware.category where name=?");) {
      selectStatement.setString(1, categoryName);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String name = rs.getString("name");
        Long categoryId = rs.getLong("id");
        CategoryDetails category = new CategoryDetails();
        category.setName(name);
        category.setId(categoryId);
        return category;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
      return null;
    }
    return null;
  }

  public String insertCategory(Category category) {
    boolean categoryExists = loadCategoryByName(category.getName()) != null;
    if (categoryExists) {
      return "category already exists in the database";
    }
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement insertStatement = conn.prepareStatement("insert into foodware.category (name,group_id) values (?,?)");) {
      insertStatement.setString(1, category.getName());
      insertStatement.setLong(2, category.getGroup_id());
      int rs = insertStatement.executeUpdate();
      if (rs!=0) {
        return "category successfully inserted";
      }
    } catch (SQLException throwables) {
      log.error("could not insert the category " + throwables.getMessage());
      return null;
    }
    return null;
  }

  public List<CategoryDetails> loadAllCategories() {
    List<CategoryDetails> categories = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select  id,name,group_id from foodware.category");) {
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String name = rs.getString("name");
        Long categoryId = rs.getLong("id");
        int groupId = rs.getInt("group_id");
        CategoryDetails category = new CategoryDetails();
        category.setName(name);
        category.setId(categoryId);
        category.setGroup(groupService.loadGroupById(groupId));
        categories.add(category);
      }
      return categories;
    } catch (SQLException e) {
      log.error("error while trying to get category information from database");
      return null;
    }
  }
}
