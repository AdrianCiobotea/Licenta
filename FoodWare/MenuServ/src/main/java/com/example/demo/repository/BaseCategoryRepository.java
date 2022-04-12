package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCategoryRepository extends CrudRepository<Category, Integer> {
}
