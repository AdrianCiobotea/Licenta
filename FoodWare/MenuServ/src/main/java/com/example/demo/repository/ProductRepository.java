package com.example.demo.repository;

import com.example.demo.entity.Product;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends org.springframework.data.repository.Repository<Product,Integer> {
  List<Product> findProductByCategoryIdOrderByName(int categoryId);
}
