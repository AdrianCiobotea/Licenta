package com.example.demo.service;

import com.example.demo.dto.ProductDetails;
import com.example.demo.entity.Product;
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
public class ProductService {

  @Autowired
  private CategoryService categoryService;

  @Value("${spring.datasource.password}")
  private String databasePassword;
  @Value("${spring.datasource.username}")
  private String databaseUserName;
  @Value("${spring.datasource.url}")
  private String databaseUrl;

  public String insertProduct(Product product) {
    boolean productExists = loadProductByName(product.getName()) != null;
    if (productExists) {
      return "product already exists in the database";
    }

    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement insertStatement = conn.prepareStatement(
             "insert into foodware.product (name,price,description,category_id,extra) values (?,?,?,?,?)");) {
      insertStatement.setString(1, product.getName());
      insertStatement.setDouble(2, product.getPrice());
      insertStatement.setString(3, product.getDescription());
      insertStatement.setInt(4, product.getCategory_id());
      insertStatement.setBoolean(5, product.getExtra());
      int rs = insertStatement.executeUpdate();
      if (rs!=0) {
        return "product successfully inserted";
      }
    } catch (SQLException throwables) {
      log.error("could not insert the product " + throwables.getMessage());
      return null;
    }
    return null;
  }

  public ProductDetails loadProductByName(String name) {
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select id,name,price,description,extra,category_id from foodware.product where name=?");) {
      selectStatement.setString(1, name);
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String productName = rs.getString("name");
        Double price = rs.getDouble("price");
        String description = rs.getString("description");
        Boolean extra = rs.getBoolean("extra");
        Long id = rs.getLong("id");
        int categoryId = rs.getInt("category_id");

        ProductDetails product = new ProductDetails();
        product.setName(productName);
        product.setPrice(price);
        product.setDescription(description);
        product.setExtra(extra);
        product.setCategory(categoryService.loadCategoryById(categoryId));
        product.setId(id);

        return product;
      }
    } catch (SQLException e) {
      log.error("error while trying to get product information from database");
      return null;
    }
    return null;
  }

  public List<ProductDetails> loadAllProducts() {
    List<ProductDetails> products = new ArrayList<>();
    try (Connection conn = DriverManager.getConnection(databaseUrl, databaseUserName, databasePassword);
         PreparedStatement selectStatement = conn.prepareStatement("select id,name,price,description,extra,category_id from foodware.product");) {
      ResultSet rs = selectStatement.executeQuery();
      while (rs.next()) {
        String productName = rs.getString("name");
        Double price = rs.getDouble("price");
        String description = rs.getString("description");
        Boolean extra = rs.getBoolean("extra");
        Long id = rs.getLong("id");
        int categoryId = rs.getInt("category_id");

        ProductDetails product = new ProductDetails();
        product.setName(productName);
        product.setPrice(price);
        product.setDescription(description);
        product.setExtra(extra);
        product.setCategory(categoryService.loadCategoryById(categoryId));
        product.setId(id);

        products.add(product);
      }
      return products;
    } catch (SQLException e) {
      log.error("error while trying to get product information from database");
      return null;
    }
  }
}
