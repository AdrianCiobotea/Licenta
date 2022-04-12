package com.example.demo.repository;

import com.example.demo.entity.Category;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends org.springframework.data.repository.Repository<Category,Integer> {
  List<Category> findCategoriesByGroupId(int groupId);

}